package com.madaoo.model.dto.article;

import com.madaoo.model.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ar_category", description = "文章分类")
public class CategoryDto extends BasePojo implements Serializable {

	@ApiModelProperty("文章分类表主键")
	private String id;

	@ApiModelProperty("父ID")
	private String parentId;

	@ApiModelProperty("分类名称")
	@NotNull(message = "分类名称不能为空")
	private String name;

	@ApiModelProperty("分类简介")
	@NotNull(message = "分类简介不能为空")
	private String summary;

	@ApiModelProperty("用户ID")
	private String userId;

	@ApiModelProperty(value = "状态", example = "1")
	private Integer state = 1;

	@ApiModelProperty(value = "文章数量", example = "1")
	private Integer articleCount;
}