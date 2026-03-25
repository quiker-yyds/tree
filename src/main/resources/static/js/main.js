/**
 * 主JavaScript文件 - 通用交互逻辑
 * 包含导航栏、滚动效果、移动端适配等基础功能
 */

document.addEventListener('DOMContentLoaded', function() {
    // 初始化导航栏功能
    initNavigation();
    
    // 初始化平滑滚动
    initSmoothScroll();
    
    // 初始化返回顶部按钮
    initBackToTop();
    
    // 初始化页面加载动画
    initPageAnimations();
});

/**
 * 导航栏功能初始化
 * 包含移动端菜单切换和链接高亮
 */
function initNavigation() {
    const hamburger = document.querySelector('.hamburger');
    const navLinks = document.querySelector('.nav-links');

    if (hamburger && navLinks) {
        // 移动端菜单切换
        hamburger.addEventListener('click', function() {
            navLinks.classList.toggle('active');
            // 添加动画效果
            this.classList.toggle('active');
        });

        // 点击链接时关闭移动端菜单
        navLinks.querySelectorAll('a').forEach(link => {
            link.addEventListener('click', function() {
                if (navLinks.classList.contains('active')) {
                    navLinks.classList.remove('active');
                    hamburger.classList.remove('active');
                }
            });
        });
    }

    // 导航栏高亮当前页面
    highlightActiveNavigation();
}

/**
 * 高亮当前页面导航链接
 * 根据当前URL或滚动位置高亮对应链接
 */
function highlightActiveNavigation() {
    const navLinks = document.querySelectorAll('.nav-links a');
    const currentHash = window.location.hash;
    
    navLinks.forEach(link => {
        const href = link.getAttribute('href');
        
        // 移除所有active类
        link.classList.remove('active');
        
        // 根据hash高亮当前链接
        if (currentHash && href.includes(currentHash)) {
            link.classList.add('active');
        } else if (!currentHash && href === '#home') {
            link.classList.add('active');
        }
    });

    // 滚动时更新高亮（可选功能）
    if (navLinks.length > 0) {
        window.addEventListener('scroll', debounce(updateNavigationHighlight, 100));
    }
}

/**
 * 根据滚动位置更新导航高亮
 */
function updateNavigationHighlight() {
    const sections = document.querySelectorAll('section[id]');
    const navLinks = document.querySelectorAll('.nav-links a');
    
    let currentSection = '';
    
    sections.forEach(section => {
        const sectionTop = section.offsetTop;
        const sectionHeight = section.clientHeight;
        
        if (window.pageYOffset >= sectionTop - 100) {
            currentSection = section.getAttribute('id');
        }
    });
    
    navLinks.forEach(link => {
        link.classList.remove('active');
        if (link.getAttribute('href') === `#${currentSection}`) {
            link.classList.add('active');
        }
    });
}

/**
 * 平滑滚动功能初始化
 * 为所有锚点链接添加平滑滚动效果
 */
function initSmoothScroll() {
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            
            const targetId = this.getAttribute('href');
            const target = document.querySelector(targetId);
            
            if (target) {
                // 计算目标位置（考虑固定导航栏）
                const targetPosition = target.offsetTop - 80;
                
                window.scrollTo({
                    top: targetPosition,
                    behavior: 'smooth'
                });
                
                // 更新URL hash（不触发默认行为）
                history.pushState(null, null, targetId);
            }
        });
    });
}

/**
 * 返回顶部按钮初始化
 * 创建并管理返回顶部按钮的显示和交互
 */
function initBackToTop() {
    // 创建返回顶部按钮
    const backToTop = document.createElement('div');
    backToTop.className = 'back-to-top';
    backToTop.innerHTML = '<i class="fas fa-arrow-up"></i>';
    backToTop.title = '返回顶部';
    document.body.appendChild(backToTop);

    // 滚动显示/隐藏按钮
    window.addEventListener('scroll', debounce(function() {
        if (window.pageYOffset > 300) {
            backToTop.classList.add('visible');
        } else {
            backToTop.classList.remove('visible');
        }
    }, 50));

    // 点击返回顶部
    backToTop.addEventListener('click', function() {
        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        });
    });
}

/**
 * 页面加载动画初始化
 * 为页面元素添加渐显动画效果
 */
function initPageAnimations() {
    // 观察器配置
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    // 创建观察器
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('animate-in');
                
                // 可选：只动画一次
                // observer.unobserve(entry.target);
            }
        });
    }, observerOptions);

    // 观察需要动画的元素
    const animateElements = document.querySelectorAll('.content-card, .tip-card, .function-item, .reflection-card');
    animateElements.forEach(el => observer.observe(el));
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

/**
 * 页面加载状态管理
 * 显示/隐藏加载状态
 */
const LoadingManager = {
    show(message = '加载中...') {
        const loading = document.createElement('div');
        loading.className = 'loading-overlay';
        loading.innerHTML = `
            <div class="loading-content">
                <i class="fas fa-spinner fa-spin"></i>
                <p>${message}</p>
            </div>
        `;
        document.body.appendChild(loading);
    },
    
    hide() {
        const loading = document.querySelector('.loading-overlay');
        if (loading) {
            loading.remove();
        }
    }
};

// 导出到全局作用域（供其他脚本使用）
window.LoadingManager = LoadingManager;
window.debounce = debounce;
window.throttle = throttle;