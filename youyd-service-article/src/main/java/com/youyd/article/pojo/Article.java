package com.youyd.article.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;


 /**
 * @description: 文章板块: 文章类
 * @author: LGG
 * @create: 2019-01-29
 **/

@ApiModel(value="article", description="文章类")
public class Article extends BasePojo implements Serializable {

	 /**
	  * ID
	  */
	 @TableId(type = IdType.ID_WORKER)
	 private Long id;

	 /**
	  * 专栏ID
	  */
	 private Integer categoryId;

	 /**
	  * 用户ID
	  */
	 private String userId;

	 /**
	  * 标题
	  */
	 private String title;

	 /**
	  * 文章封面
	  */
	 private String image;

	 /**
	  * 是否公开
	  */
	 private String isPublic;

	 /**
	  * 是否置顶
	  */
	 private String isTop;

	 /**
	  * 浏览量
	  */
	 private Integer visits;

	 /**
	  * 点赞数
	  */
	 private Integer upvote;

	 /**
	  * 评论数
	  */
	 private Integer comment;

	 /**
	  * 审核状态
	  */
	 private String reviewState;

	 /**
	  * URL
	  */
	 private String url;

	 /**
	  * 类型
	  */
	 private String type;

	 /**
	  * 热度
	  */
	 private String importance;

	 /**
	  * 文章描述（概述）
	  */
	 private String description;

	 /**
	  * 关键字
	  */
	 private String keywords;

	 /**
	  * 来源（1：原创，2：转载，3：混撰）
	  */
	 private String origin;

	 /**
	  * 文章正文
	  */
	 private String content;

	 /**
	  * 文章分类
	  */
	 @TableField(exist=false)
	 private Category category;


	 private static final long serialVersionUID = 1L;

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public Integer getCategoryId() {
		 return categoryId;
	 }

	 public void setCategoryId(Integer categoryId) {
		 this.categoryId = categoryId;
	 }

	 public String getUserId() {
		 return userId;
	 }

	 public void setUserId(String userId) {
		 this.userId = userId;
	 }

	 public String getTitle() {
		 return title;
	 }

	 public void setTitle(String title) {
		 this.title = title;
	 }

	 public String getImage() {
		 return image;
	 }

	 public void setImage(String image) {
		 this.image = image;
	 }

	 public String getIsPublic() {
		 return isPublic;
	 }

	 public void setIsPublic(String isPublic) {
		 this.isPublic = isPublic;
	 }

	 public String getIsTop() {
		 return isTop;
	 }

	 public void setIsTop(String isTop) {
		 this.isTop = isTop;
	 }

	 public Integer getVisits() {
		 return visits;
	 }

	 public void setVisits(Integer visits) {
		 this.visits = visits;
	 }

	 public Integer getComment() {
		 return comment;
	 }

	 public void setComment(Integer comment) {
		 this.comment = comment;
	 }

	 public String getReviewState() {
		 return reviewState;
	 }

	 public void setReviewState(String reviewState) {
		 this.reviewState = reviewState;
	 }

	 public String getUrl() {
		 return url;
	 }

	 public void setUrl(String url) {
		 this.url = url;
	 }

	 public String getType() {
		 return type;
	 }

	 public void setType(String type) {
		 this.type = type;
	 }

	 public String getImportance() {
		 return importance;
	 }

	 public void setImportance(String importance) {
		 this.importance = importance;
	 }

	 public Integer getUpvote() {
		 return upvote;
	 }

	 public void setUpvote(Integer upvote) {
		 this.upvote = upvote;
	 }

	 public String getKeywords() {
		 return keywords;
	 }

	 public void setKeywords(String keywords) {
		 this.keywords = keywords;
	 }

	 public String getDescription() {
		 return description;
	 }

	 public void setDescription(String description) {
		 this.description = description;
	 }

	 public String getOrigin() {
		 return origin;
	 }

	 public void setOrigin(String origin) {
		 this.origin = origin;
	 }

	 public Category getCategory() {
		 return category;
	 }

	 public void setCategory(Category category) {
		 this.category = category;
	 }

	 public String getContent() {
		 return content;
	 }

	 public void setContent(String content) {
		 this.content = content;
	 }
 }