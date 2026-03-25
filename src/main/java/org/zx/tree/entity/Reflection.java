package org.zx.tree.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reflections")
public class Reflection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String authorName;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String imageCaption;

    @Column(columnDefinition = "TEXT")
    private String secondImageUrl;

    @Column(columnDefinition = "TEXT")
    private String secondImageCaption;

    @Column(columnDefinition = "TEXT")
    private String thirdImageUrl;

    @Column(columnDefinition = "TEXT")
    private String thirdImageCaption;

    private String publishDate;

    @Column(columnDefinition = "TEXT")
    private String highlightQuote;

    @Column(nullable = false)
    private Boolean published = false;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getImageCaption() { return imageCaption; }
    public void setImageCaption(String imageCaption) { this.imageCaption = imageCaption; }

    public String getSecondImageUrl() { return secondImageUrl; }
    public void setSecondImageUrl(String secondImageUrl) { this.secondImageUrl = secondImageUrl; }

    public String getSecondImageCaption() { return secondImageCaption; }
    public void setSecondImageCaption(String secondImageCaption) { this.secondImageCaption = secondImageCaption; }

    public String getThirdImageUrl() { return thirdImageUrl; }
    public void setThirdImageUrl(String thirdImageUrl) { this.thirdImageUrl = thirdImageUrl; }

    public String getThirdImageCaption() { return thirdImageCaption; }
    public void setThirdImageCaption(String thirdImageCaption) { this.thirdImageCaption = thirdImageCaption; }

    public String getPublishDate() { return publishDate; }
    public void setPublishDate(String publishDate) { this.publishDate = publishDate; }

    public String getHighlightQuote() { return highlightQuote; }
    public void setHighlightQuote(String highlightQuote) { this.highlightQuote = highlightQuote; }

    public Boolean getPublished() { return published; }
    public void setPublished(Boolean published) { this.published = published; }

    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }

    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
}