package com.codeway.model.dto.base;

import com.codeway.model.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 音乐实体
 **/

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MusicDto extends BasePojo implements Serializable {

	private String id;

	private String parentId;

	@ApiModelProperty(value = "编码", example = "1")
	@NotNull(message = "编码不能为空")
	private String code;

	@ApiModelProperty("名称")
	@NotNull(message = "编码不能为空")
	private String name;

	@ApiModelProperty("描述")
	@NotNull(message = "描述不能为空")
	private String description;

	@ApiModelProperty(value = "状态", example = "1")
	private Integer state;

	@ApiModelProperty("类型")
	@NotNull(message = "类型不能为空")
	private String type;

	private static final long serialVersionUID = 1L;

}