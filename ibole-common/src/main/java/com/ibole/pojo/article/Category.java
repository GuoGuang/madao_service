package com.ibole.pojo.article;

import com.ibole.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ApiModel(value = "ar_category", description = "文章分类")
@Entity
@Table(name = "ar_category")
public class Category extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
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

    @ApiModelProperty("状态")
	private Integer state;

}