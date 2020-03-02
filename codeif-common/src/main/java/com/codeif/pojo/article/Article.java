package com.codeif.pojo.article;

import com.codeif.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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


/**
 * 文章板块: 文章类
 **/
@Getter
@Setter
@ApiModel(value = "article", description = "文章类")
@Entity
@JsonIgnoreProperties(value = { "article" })

@Table(name = "ar_article")
public class Article extends BasePojo implements Serializable {


    @ApiModelProperty(value = "文章分类")
	@JoinColumn(name = "category_id",foreignKey=@ForeignKey(name="null"))
	@ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
	private Category category;

    @ApiModelProperty(value = "文章标签")
    @JoinTable(
		    name = "ar_article_tags",
		    joinColumns = {@JoinColumn(name="article_id",referencedColumnName = "id",foreignKey=@ForeignKey(name="null"))},
		    inverseJoinColumns = {@JoinColumn(name = "tag_id",referencedColumnName = "id",foreignKey=@ForeignKey(name="null"))}
    )
    @JsonManagedReference
	@ManyToMany
    private Set<Tags> tags = new HashSet<>();

    @ApiModelProperty(value = "推荐阅读",example = "1")
    @Transient
    private String related;

    @ApiModelProperty("用户名")
    @Transient
    private String userName;


    @ApiModelProperty("ID")
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeif.config.IdGeneratorConfig")
    @Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
    private String id;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("标签")
    @NotNull(message = "标签不能为空")
    private String label;

    @ApiModelProperty("标题")
    @NotNull(message = "标题不能为空")
    private String title;

    @ApiModelProperty("文章封面")
    private String image;

    @ApiModelProperty(value = "是否公开",example = "1")
    private Integer isPublic;

    @ApiModelProperty(value = "是否置顶",example = "1")
    private Integer isTop;

    @ApiModelProperty(value = "浏览量",example = "1")
    private Integer visits;

    @ApiModelProperty(value = "点赞数",example = "1")
    private Integer upvote;

    @ApiModelProperty("评论数")
    private Integer comment;

    @ApiModelProperty(value = "审核状态",example = "1")
    private Integer reviewState;

    @ApiModelProperty("URL")
    private String url;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("热度")
    @Column(precision = 2, scale = 1)
    private float importance;

    @ApiModelProperty("文章描述（概述）")
    @NotNull(message = "概述不能为空")
    private String description;

    @ApiModelProperty("关键字")
    private String keywords;

    @ApiModelProperty("来源（1：原创，2：转载，3：混撰）")
    @NotNull(message = "来源不能为空")
    private Integer origin;

    @ApiModelProperty("文章正文")
    @NotNull(message = "内容不能为空")
    @Lob
    @Column(columnDefinition = "text")
    private String content;

	@Override
	public String toString() {
		return "Article{" +
				"category=" + category +
				", related='" + related + '\'' +
				", userName='" + userName + '\'' +
				", id='" + id + '\'' +
				", userId='" + userId + '\'' +
				", label='" + label + '\'' +
				", title='" + title + '\'' +
				", image='" + image + '\'' +
				", isPublic=" + isPublic +
				", isTop=" + isTop +
				", visits=" + visits +
				", upvote=" + upvote +
				", comment=" + comment +
				", reviewState=" + reviewState +
				", url='" + url + '\'' +
				", type=" + type +
				", importance=" + importance +
				", description='" + description + '\'' +
				", keywords='" + keywords + '\'' +
				", origin=" + origin +
				", content='" + content + '\'' +
				'}';
	}
}