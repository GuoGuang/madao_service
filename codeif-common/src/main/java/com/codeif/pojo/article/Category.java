package com.codeif.pojo.article;

import com.codeif.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ApiModel(value = "ar_category", description = "文章分类")
@Entity
@JsonIgnoreProperties(value = { "category" })

@Table(name = "ar_category")
public class Category extends BasePojo implements Serializable {

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "category")
	private Set<Article> article = new HashSet<>();


    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeif.config.IdGeneratorConfig")
    @ApiModelProperty("文章分类表主键")
    @Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
    private String id;

    @ApiModelProperty("父ID")
    @Column(length = 20)
    private String parentId;

    @ApiModelProperty("分类名称")
    @NotNull(message = "分类名称不能为空")
    @Column(length = 20)
    private String name;

    @ApiModelProperty("分类简介")
    @NotNull(message = "分类简介不能为空")
    @Column(length = 200)
    private String summary;

    @ApiModelProperty("用户ID")
    @Column(length = 20)
	private String userId;

    @ApiModelProperty(value = "状态",example = "1")
    @Column(length = 1)
    private Integer state = 1;

}