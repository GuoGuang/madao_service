package com.youyd.pojo.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


 /**
 * @description: 文章板块: 文章类
 * @author: LGG
 * @create: 2019-01-29
 **/
@Getter
@Setter
@ToString
@ApiModel(value="article", description="文章类")
public class Article extends BasePojo implements Serializable {

	 /**
	  * 文章分类
	  */
	 @TableField(exist=false)
	 private Category category;
	 /**
	  * ID
	  */
	 @TableId(type = IdType.ID_WORKER_STR)
	 private String id;

	 /**
	  * 分类ID
	  */
	 private String categoryId;

	 /**
	  * 用户ID
	  */
	 private String userId;

	 /**
	  * 标签
	  */
	 private String label;

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
	 private Integer isPublic;

	 /**
	  * 是否置顶
	  */
	 private Integer isTop;

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
	 private Integer reviewState;

	 /**
	  * URL
	  */
	 private String url;

	 /**
	  * 类型
	  */
	 private Integer type;

	 /**
	  * 热度
	  */
	 private Integer importance;

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
	 private Integer origin;

	 /**
	  * 文章正文
	  */
	 private String content;

 }