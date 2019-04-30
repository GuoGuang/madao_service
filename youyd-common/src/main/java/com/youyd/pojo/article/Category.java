package com.youyd.pojo.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/***
 * 文章分类
 * @author LGG
 */

@Getter
@Setter
@ApiModel(value="article", description="文章类")
public class Category extends BasePojo implements Serializable {


	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	private Integer parentId;

	/**
	 * 分类名称
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

}