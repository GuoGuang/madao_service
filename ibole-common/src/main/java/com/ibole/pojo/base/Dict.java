package com.ibole.pojo.base;

import com.ibole.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "ba_dict")
public class Dict extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
    @ApiModelProperty("字典表表主键")
    private String id;

    @ApiModelProperty("父id")
    private String parentId;

    @ApiModelProperty("编码")
    @NotNull(message = "编码不能为空")
    private String code;

    @ApiModelProperty("名称")
    @NotNull(message = "编码不能为空")
    private String name;

    @ApiModelProperty("描述")
    @NotNull(message = "描述不能为空")
    private String description;

    @ApiModelProperty("状态")
    private Integer state;

    @ApiModelProperty("类型")
    @NotNull(message = "类型不能为空")
    private String type;

    private static final long serialVersionUID = 1L;

}