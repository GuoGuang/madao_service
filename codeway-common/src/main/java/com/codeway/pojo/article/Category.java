package com.codeway.pojo.article;

import com.codeway.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ApiModel(value = "ar_category", description = "文章分类")
@Entity
@Table(name = "ar_category")
public class Category extends BasePojo implements Serializable {

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "category")
    @org.hibernate.annotations.ForeignKey(name = "none")
	private Set<Article> article = new HashSet<>();


    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
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

	@ApiModelProperty(value = "状态", example = "1")
	@Column(length = 1)
	private Integer state = 1;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Category)) return false;
		if (!super.equals(o)) return false;
		Category category = (Category) o;
		return id.equals(category.id) &&
				Objects.equals(parentId, category.parentId) &&
				Objects.equals(name, category.name) &&
				Objects.equals(summary, category.summary) &&
				Objects.equals(userId, category.userId) &&
				Objects.equals(state, category.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, parentId, name, summary, userId, state);
	}
}