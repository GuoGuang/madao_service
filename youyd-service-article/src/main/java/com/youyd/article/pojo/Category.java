package com.youyd.article.pojo;

import com.youyd.pojo.BasePojo;

import java.io.Serializable;

/***
 * 文章分类
 */

public class Category extends BasePojo implements Serializable {

		private Integer id; // ID

		private Integer parentId; // 父ID

		private String name; // v

		private String summary; // 分类简介

		private String userId; // 用户ID

		private String state; // 状态

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