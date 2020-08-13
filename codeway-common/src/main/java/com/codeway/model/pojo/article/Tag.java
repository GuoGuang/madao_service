package com.codeway.model.pojo.article;

import com.codeway.model.BasePojo;
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
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "ar_tag", indexes = {@Index(name = "tag_name", columnList = "name")})
@ApiModel(value = "article", description = "标签类")
public class Tag extends BasePojo implements Serializable {

	/**
	 * 接收连接查询结果专用字段
	 */
	@Transient
	@ApiModelProperty(value = "标签下文章数量", example = "1")
	private Long tagsCount;

	@ManyToMany(mappedBy = "tags")
	@JsonIgnore
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Set<Article> articles = new HashSet<>();

	@Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
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
	@Column(length = 30)
	private String color;

	@ApiModelProperty("状态")
	@Column(length = 1)
	private Integer state;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Tag)) return false;
		if (!super.equals(o)) return false;
		Tag tags = (Tag) o;
		return Objects.equals(tagsCount, tags.tagsCount) &&
				id.equals(tags.id) &&
				Objects.equals(name, tags.name) &&
				Objects.equals(slug, tags.slug) &&
				Objects.equals(description, tags.description) &&
				Objects.equals(icon, tags.icon) &&
				Objects.equals(color, tags.color) &&
				Objects.equals(state, tags.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), tagsCount, id, name, slug, description, icon, color, state);
	}
}