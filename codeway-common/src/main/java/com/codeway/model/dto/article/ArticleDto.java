package com.codeway.model.dto.article;

import com.codeway.enums.ArticleAuditStatus;
import com.codeway.enums.ArticleOriginType;
import com.codeway.model.BasePojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "article", description = "文章类")
public class ArticleDto extends BasePojo implements Serializable {

	@ApiModelProperty(value = "推荐阅读", example = "1")
	@JsonIgnoreProperties("related")
	private List<ArticleDto> related;

	private List<TagDto> tags;


	@ApiModelProperty(value = "分类id", example = "1")
	private String categoryId;

	@ApiModelProperty(value = "标签id", example = "1")
	private String tagsId;

	@ApiModelProperty("用户名")
	private String userName;

	@ApiModelProperty("ID")
	private String id;

	@ApiModelProperty("用户ID")
	private String userId;

	@ApiModelProperty("标题")
	@NotNull(message = "标题不能为空")
	private String title;

	@ApiModelProperty("文章封面")
	private String thumb;

	@ApiModelProperty(value = "是否公开", example = "1")
	private Boolean isPublic;

	@ApiModelProperty(value = "是否置顶", example = "1")
	private Boolean isTop;

	@ApiModelProperty(value = "浏览量", example = "1")
	private Integer visits;

	@ApiModelProperty(value = "点赞数", example = "1")
	private Integer upvote;

	@ApiModelProperty("评论数")
	private Integer comment;

	@ApiModelProperty(value = "审核状态", example = "1")
	private ArticleAuditStatus reviewState;

	@ApiModelProperty("URL")
	private String url;

	@ApiModelProperty("热度")
	private float importance;

	@ApiModelProperty("文章描述")
	@NotNull(message = "概述不能为空")
	private String description;

	@ApiModelProperty("关键字")
	private String keywords;

	@ApiModelProperty("来源")
	@NotNull(message = "来源不能为空")
	private ArticleOriginType origin;

	@ApiModelProperty("文章正文")
	@NotNull(message = "内容不能为空")
	private String content;
}