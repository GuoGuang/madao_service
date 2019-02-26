package com.youyd.article.pojo;

import com.youyd.pojo.BasePojo;

import java.io.Serializable;

/**
 * 回答表
 */

public class Answers extends BasePojo implements Serializable {
    private Integer id; // 答案表id
    private Integer questionId;// 问题表ID
    private String content;// 答案内容
    private Integer userId;// 回答人ID
    private String nickName;// 回答人昵称

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}