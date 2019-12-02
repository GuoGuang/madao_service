package com.ibole.pojo.article;

import com.ibole.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/***
 * 文章分类
 */

@Getter
@Setter
@ApiModel(value = "article", description = "文章类")
@Entity
@Table(name = "ar_category")
public class Category extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
    private String id;

    private String parentId;

    @NotNull(message = "分类名称不能为空")
    private String name; // 分类名称

    @NotNull(message = "分类简介不能为空")
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