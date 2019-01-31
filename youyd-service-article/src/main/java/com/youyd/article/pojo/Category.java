package com.youyd.article.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/***
 * 文章分类
 * @author LGG
 */

@ApiModel(value="article", description="文章类")
public class Category extends BasePojo implements Serializable {

	private Integer id;

	/**
	 * 父ID
	 */
	@TableId(type = IdType.ID_WORKER)
	private Integer parentId;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 分类简介
	 */
	private String summary;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 状态
	 */
	private String state;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}