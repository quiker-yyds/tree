/**
 * 通用工具函数库
 * 提供页面间共享的工具方法
 */

/**
 * HTML转义，防止XSS攻击
 * @param {string} text - 待转义的文本
 * @returns {string} 转义后的文本
 */
function escapeHtml(text) {
    if (!text) return '';
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

/**
 * 显示提示消息
 * @param {string} message - 提示内容
 * @param {string} type - 消息类型：'success' | 'error' | 'info'
 * @param {number} duration - 显示时长（毫秒）
 */
function showToast(message, type = 'info', duration = 3000) {
    const toast = document.createElement('div');
    toast.className = `toast toast-${type}`;
    toast.textContent = message;
    toast.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        padding: 15px 25px;
        border-radius: 10px;
        color: #fff;
        font-weight: 600;
        z-index: 2000;
        animation: slideIn 0.3s ease;
        background: ${type === 'success' ? '#28a745' : type === 'error' ? '#dc3545' : '#17a2b8'};
    `;
    
    // 添加动画样式（如果页面没有）
    if (!document.getElementById('toast-styles')) {
        const style = document.createElement('style');
        style.id = 'toast-styles';
        style.textContent = `
            @keyframes slideIn {
                from { opacity: 0; transform: translateX(100px); }
                to { opacity: 1; transform: translateX(0); }
            }
            @keyframes slideOut {
                from { opacity: 1; transform: translateX(0); }
                to { opacity: 0; transform: translateX(100px); }
            }
        `;
        document.head.appendChild(style);
    }
    
    document.body.appendChild(toast);
    
    setTimeout(() => {
        toast.style.animation = 'slideOut 0.3s ease forwards';
        setTimeout(() => toast.remove(), 300);
    }, duration);
}

/**
 * 获取URL查询参数
 * @param {string} name - 参数名
 * @returns {string|null} 参数值
 */
function getQueryParam(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}

/**
 * 格式化日期显示
 * @param {string} dateStr - 日期字符串
 * @returns {string} 格式化后的日期
 */
function formatDate(dateStr) {
    if (!dateStr) return '';
    return escapeHtml(dateStr);
}

/**
 * 加载组件到指定容器
 * @param {string} componentName - 组件名称（不含.html后缀）
 * @param {string} containerSelector - 容器选择器
 * @param {Object} options - 传递给组件的选项
 * @returns {Promise} 加载完成后的Promise
 */
async function loadComponent(componentName, containerSelector, options = {}) {
    const container = document.querySelector(containerSelector);
    if (!container) {
        console.error(`容器 ${containerSelector} 不存在`);
        return;
    }
    
    try {
        // 动态确定组件路径
        const isRootPage = window.location.pathname.endsWith('/') || 
                          window.location.pathname.endsWith('index.html') ||
                          window.location.pathname.endsWith('reflection.html');
        
        const basePath = isRootPage ? 'components/' : '../components/';
        const response = await fetch(`${basePath}${componentName}.html`);
        
        if (!response.ok) throw new Error('组件加载失败');
        
        let html = await response.text();
        
        // 简单的模板替换（如果需要）
        Object.keys(options).forEach(key => {
            html = html.replace(new RegExp(`{{${key}}}`, 'g'), options[key]);
        });
        
        container.innerHTML = html;
        
        // 执行组件内的脚本
        executeComponentScripts(container);
        
    } catch (error) {
        console.error(`加载组件 ${componentName} 失败:`, error);
        container.innerHTML = `<div class="error">组件加载失败</div>`;
    }
}

/**
 * 执行容器内的脚本
 * @param {HTMLElement} container - 容器元素
 */
function executeComponentScripts(container) {
    const scripts = container.querySelectorAll('script');
    scripts.forEach(oldScript => {
        const newScript = document.createElement('script');
        
        // 复制脚本属性
        Array.from(oldScript.attributes).forEach(attr => {
            newScript.setAttribute(attr.name, attr.value);
        });
        
        // 复制脚本内容
        newScript.textContent = oldScript.textContent;
        
        // 替换旧脚本
        oldScript.parentNode.replaceChild(newScript, oldScript);
    });
}

/**
 * 加载多个组件
 * @param {Array} components - 组件数组，格式: [{name: 'navbar', selector: '#navbar-container'}, ...]
 * @returns {Promise} 所有组件加载完成后的Promise
 */
async function loadComponents(components) {
    await Promise.all(
        components.map(comp => loadComponent(comp.name, comp.selector, comp.options))
    );
}

/**
 * API请求封装
 * @param {string} url - 请求URL
 * @param {Object} options - fetch选项
 * @returns {Promise} 请求结果
 */
async function apiRequest(url, options = {}) {
    const defaultOptions = {
        headers: {
            'Content-Type': 'application/json'
        }
    };
    
    const mergedOptions = { ...defaultOptions, ...options };
    
    try {
        const response = await fetch(url, mergedOptions);
        
        if (!response.ok) {
            const error = await response.json().catch(() => ({}));
            throw new Error(error.message || `请求失败: ${response.status}`);
        }
        
        return await response.json();
    } catch (error) {
        console.error('API请求错误:', error);
        throw error;
    }
}

/**
 * 防抖函数
 * @param {Function} func - 要执行的函数
 * @param {number} wait - 等待时间（毫秒）
 * @returns {Function} 防抖后的函数
 */
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

/**
 * 节流函数
 * @param {Function} func - 要执行的函数
 * @param {number} limit - 限制时间（毫秒）
 * @returns {Function} 节流后的函数
 */
function throttle(func, limit) {
    let inThrottle;
    return function() {
        const args = arguments;
        const context = this;
        if (!inThrottle) {
            func.apply(context, args);
            inThrottle = true;
            setTimeout(() => inThrottle = false, limit);
        }
    };
}