package com.madao.model.dto.article;

import com.madao.model.BasePojo;
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
@ApiModel(value = "article", description = "标签类")
public class TagDto extends BasePojo implements Serializable {

	@ApiModelProperty(value = "标签下文章数量", example = "1")
	private Integer tagsCount;

	private String id;

	@ApiModelProperty("标签名称")
	@NotNull(message = "标签名称不能为空")
	private String name;

	@ApiModelProperty("英文名称")
	@NotNull(message = "英文名称不能为空")
	private String slug;

	@ApiModelProperty("描述")
	@NotNull(message = "描述不能为空")
	private String description;

	@ApiModelProperty("标签图标")
	private String icon;

	@ApiModelProperty("标签颜色，前台显示")
	private String color;

	@ApiModelProperty("状态")
	private Integer state;

}