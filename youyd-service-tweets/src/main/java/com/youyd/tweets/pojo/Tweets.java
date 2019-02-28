package com.youyd.tweets.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 吐槽表
 */

public class Tweets extends BasePojo implements Serializable {

	@TableId(type = IdType.ID_WORKER_STR)
    private String id; // 吐槽表ID

    private String content; // 吐槽内容

    private Date publishTime; // 发布日期(创建时间)

    private String userId; // 发布人ID

    private String nickName; // 发布人昵称

    private Long visitsCount; // 浏览量

    private Long thumbUpCount; // 点赞数

    private Long shareCount; // 分享数

    private Long replyCount; // 回复数

    private Integer isVisible; // 是否可见

    private String parentId; // 上级ID

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getVisitsCount() {
        return visitsCount;
    }

    public void setVisitsCount(Long visitsCount) {
        this.visitsCount = visitsCount;
    }

    public Long getThumbUpCount() {
        return thumbUpCount;
    }

    public void setThumbUpCount(Long thumbUpCount) {
        this.thumbUpCount = thumbUpCount;
    }

    public Long getShareCount() {
        return shareCount;
    }

    public void setShareCount(Long shareCount) {
        this.shareCount = shareCount;
    }

    public Long getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Long replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}