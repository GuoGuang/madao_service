package com.youyd.article.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章的评论表
 */

public class Comment implements Serializable {

    private String id; // 评论表id

    private String articleId; // 外键文章表ID

    private String content; // 评论内容

    private String userId; // 评论人ID

    private String parentId; // 父评论ID(如果为0表示文章的顶级评论,每一条评论都可以被评论)

    private Date publishDate; // 评论日期

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}