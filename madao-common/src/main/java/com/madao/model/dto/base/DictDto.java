package com.madao.model.dto.base;

import com.madao.model.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DictDto extends BasePojo implements Serializable {

    @ApiModelProperty("字典表表主键")
    private String id;

    @ApiModelProperty("父id")
    private String parentId;

    @ApiModelProperty("编码")
    @NotNull(message = "编码不能为空")
    private String code;

    @ApiModelProperty("名称")
    @NotNull(message = "名称不能为空")
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