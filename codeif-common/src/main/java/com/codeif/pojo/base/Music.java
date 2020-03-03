package com.codeif.pojo.base;

import com.codeif.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 音乐实体
 **/

@Getter
@Setter
//@Entity
//@Table(name = "")
public class Music extends BasePojo implements Serializable {

    //  @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 父id
     */
    private String parentId;

    @ApiModelProperty(value = "编码",example = "1")
    @NotNull(message = "编码不能为空")
    private String code;

    @ApiModelProperty("名称")
    @NotNull(message = "编码不能为空")
    private String name;

    @ApiModelProperty("描述")
    @NotNull(message = "描述不能为空")
    private String description;

    @ApiModelProperty(value = "状态",example = "1")
    private Integer state;

    @ApiModelProperty("类型")
    @NotNull(message = "类型不能为空")
    private String type;

    private static final long serialVersionUID = 1L;

}