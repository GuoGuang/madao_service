package com.ibole.pojo.article;

import com.ibole.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


 /**
  * 文章板块: 文章类
  **/
 @Getter
 @Setter
 @ToString
 @ApiModel(value = "article", description = "文章类")
 @Entity
 @Table(name = "ar_article")
 public class Article extends BasePojo implements Serializable {

     @Transient
     private Category category; // 文章分类

     @Transient
     private String userName; // 用户名


     @Id
     @GeneratedValue(generator = "idGenerator")
     @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
     private String id; // ID

     private String categoryId; // 分类ID

     private String userId; // 用户ID

     @NotNull(message = "标签不能为空")
     private String label; // 标签

     @NotNull(message = "标题不能为空")
	 private String title; // 标题

	 private String image; // 文章封面

	 private Integer isPublic; // 是否公开

	 private Integer isTop; // 是否置顶

	 private Integer visits; // 浏览量

     private Integer upvote; // 点赞数

     private Integer comment; // 评论数

     private Integer reviewState; // 审核状态

     private String url; // URL

     private Integer type; // 类型

     @Column(precision = 2, scale = 1)
     private float importance; // 热度

     @NotNull(message = "概述不能为空")
     private String description; // 文章描述（概述）

     private String keywords; // 关键字

     @NotNull(message = "来源不能为空")
     private Integer origin; // 来源（1：原创，2：转载，3：混撰）

     @NotNull(message = "内容不能为空")
     @Lob
     @Column(columnDefinition = "text")
     private String content; // 文章正文

 }