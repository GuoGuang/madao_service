package com.youyd.tweets.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;

import java.io.Serializable;

/**
 * @description: 吐槽表
 * @author: LGG
 * @create: 2019-03-01
 **/
public class Tweets extends BasePojo implements Serializable {

	@TableId(type = IdType.ID_WORKER)
    private Integer id; // 吐槽表ID

    private String content; // 吐槽内容

    private Integer userId; // 发布人ID

    private String nickName; // 发布人昵称

    private Long visitsCount; // 浏览量

    private Long thumbUpCount; // 点赞数

    private Long shareCount; // 分享数

    private Long replyCount; // 回复数

    private Integer isVisible; // 是否可见

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

}