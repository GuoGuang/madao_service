package com.ibole.pojo.article;

import com.ibole.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ApiModel(value = "article", description = "标签类")
@Entity
@Table(name = "ar_tags")
public class Tags extends BasePojo implements Serializable {

    /**
     * 接收连接查询结果专用字段
     */
    @Transient
    @ApiModelProperty("标签下文章数量")
    private Long tagsCount;

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
    @ApiModelProperty("标签主键表ID")
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

    @ApiModelProperty("状态")
    private String state;


}