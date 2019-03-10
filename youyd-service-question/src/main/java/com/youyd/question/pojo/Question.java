package com.youyd.question.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @description: 问题表
 * @author: LGG
 * @create: 2019-02-26 16:21
 **/

@ApiModel(value="question", description="文章类")
public class Question extends BasePojo implements Serializable {

	/**
	 * ID
	 */
	@TableId(type = IdType.ID_WORKER)
    private Integer id;
    private String title;//标题
    private String content;//内容
    private Integer tagsId;//问题标签表
    private String userId;//用户ID
    private String nickName;//昵称
    private Long visits;//浏览量
    private Long thumbUp;//点赞数
    private Long reply;//回复数
    private String solve;//是否解决
    private String replyName;//回复人昵称
    private Date replyTime;//回复日期

	@TableField(exist=false)
    private ArrayList<Answers> answers; // 答案


	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTagsId() {
		return tagsId;
	}

	public void setTagsId(Integer tagsId) {
		this.tagsId = tagsId;
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

	public Long getVisits() {
		return visits;
	}

	public void setVisits(Long visits) {
		this.visits = visits;
	}

	public Long getThumbUp() {
		return thumbUp;
	}

	public void setThumbUp(Long thumbUp) {
		this.thumbUp = thumbUp;
	}

	public Long getReply() {
		return reply;
	}

	public void setReply(Long reply) {
		this.reply = reply;
	}

	public String getSolve() {
		return solve;
	}

	public void setSolve(String solve) {
		this.solve = solve;
	}

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getContent() {
		return content;
	}

	public ArrayList<Answers> getAnswers() {
		return answers;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setAnswers(ArrayList<Answers> answers) {
		this.answers = answers;
	}
}