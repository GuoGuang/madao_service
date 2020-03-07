package com.codeif.pojo.article;

import com.codeif.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "ar_tags")
@ApiModel(value = "article", description = "标签类")
public class Tags extends BasePojo implements Serializable {

    /**
     * 接收连接查询结果专用字段
     */
    @Transient
    @ApiModelProperty(value = "标签下文章数量",example = "1")
    private Long tagsCount;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private Set<Article> articles = new HashSet<>();

	@Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeif.config.IdGeneratorConfig")
    @ApiModelProperty("标签主键表ID")
    @Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
    private String id;

    @ApiModelProperty("标签名称")
    @NotNull(message = "标签名称不能为空")
    @Column(length = 20)
    private String name;

    @ApiModelProperty("英文名称")
    @NotNull(message = "英文名称不能为空")
    @Column(length = 20)
    private String slug;

    @ApiModelProperty("描述")
    @NotNull(message = "描述不能为空")
    @Column(length = 200)
    private String description;

    @ApiModelProperty("标签图标")
    @Column(length = 50)
    private String icon;

    @ApiModelProperty("标签颜色，前台显示")
    @Column(length = 20)
    private String color;

    @ApiModelProperty("状态")
    @Column(length = 1)
    private Integer state;

}