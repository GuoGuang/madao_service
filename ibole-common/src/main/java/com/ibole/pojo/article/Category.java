package com.ibole.pojo.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ibole.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/***
 * 文章分类
 */

@Getter
@Setter
@ApiModel(value="article", description="文章类")
public class Category extends BasePojo implements Serializable {

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	private String parentId;

	@NotNull(message="分类名称不能为空")
	private String name; // 分类名称

	@NotNull(message="分类简介不能为空")
	private String summary; // 分类简介

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 状态
	 */
	private Integer state;

}