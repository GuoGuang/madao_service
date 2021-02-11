package com.madao.model.dto.article;

import com.google.common.base.Objects;
import com.madao.model.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value = "article", description = "标签类")
public class TagDto extends BasePojo implements Serializable {

    @ApiModelProperty(value = "标签下文章数量", example = "1")
    private Integer tagsCount;

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

    @ApiModelProperty("标签颜色，前台显示")
    private String color;

    @ApiModelProperty("状态")
    private Integer state;

	public Integer getTagsCount() {
		return tagsCount;
	}

	public TagDto setTagsCount(Integer tagsCount) {
		this.tagsCount = tagsCount;
		return this;
	}

	public String getId() {
		return id;
	}

	public TagDto setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public TagDto setName(String name) {
		this.name = name;
		return this;
	}

	public String getSlug() {
		return slug;
	}

	public TagDto setSlug(String slug) {
		this.slug = slug;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public TagDto setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public TagDto setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public String getColor() {
		return color;
	}

	public TagDto setColor(String color) {
		this.color = color;
		return this;
	}

	public Integer getState() {
		return state;
	}

	public TagDto setState(Integer state) {
		this.state = state;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TagDto)) return false;
		if (!super.equals(o)) return false;
		TagDto tagDto = (TagDto) o;
		return Objects.equal(tagsCount, tagDto.tagsCount) && Objects.equal(id, tagDto.id) && Objects.equal(name, tagDto.name) && Objects.equal(slug, tagDto.slug) && Objects.equal(description, tagDto.description) && Objects.equal(icon, tagDto.icon) && Objects.equal(color, tagDto.color) && Objects.equal(state, tagDto.state);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), tagsCount, id, name, slug, description, icon, color, state);
	}
}