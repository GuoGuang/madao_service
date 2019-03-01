package com.youyd.tweets.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;

import java.io.Serializable;

/**
 * @description: 吐槽评论表
 * @author: LGG
 * @create: 2019-03-01
 **/
public class TweetsComment extends BasePojo implements Serializable {

    /**
     * 吐槽评论ID
     */
    @TableId(type = IdType.ID_WORKER)
    private Integer id;

    /**
     * 评论内容
     */
    private String content;


    /**
     * 评论人ID
     */
    private Integer userId;

    /**
     * 评论人昵称
     */
    private String nickName;

    /**
     * 点赞数
     */
    private Long thumbUpCount;

    /**
     * 回复数
     */
    private Long replyCount;

    /**
     * 是否可见
     */
    private Integer isVisible;


    /**
     * 吐槽表id
     */
    private Integer tweetsId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getThumbUpCount() {
        return thumbUpCount;
    }

    public void setThumbUpCount(Long thumbUpCount) {
        this.thumbUpCount = thumbUpCount;
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


    public Integer getTweetsId() {
        return tweetsId;
    }

    public void setTweetsId(Integer tweetsId) {
        this.tweetsId = tweetsId;
    }

}