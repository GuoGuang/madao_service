package com.madao.model.dto.article;

import com.google.common.base.Objects;
import com.madao.model.BasePojo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Schema(title = "article", description = "标签类")
public class TagDto extends BasePojo implements Serializable {

	@Schema(title = "标签下文章数量")
	private Integer tagsCount;

	private String id;

	@Schema(title = "标签名称")
	@NotNull(message = "标签名称不能为空")
	private String name;

	@Schema(title = "英文名称")
	@NotNull(message = "英文名称不能为空")
	private String slug;

	@Schema(title = "描述")
	@NotNull(message = "描述不能为空")
	private String description;

	@Schema(title = "标签图标")
	private String icon;

	@Schema(title = "标签颜色，前台显示")
	private String color;

	@Schema(title = "状态")
	private Integer state;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TagDto tagDto)) return false;
		if (!super.equals(o)) return false;
		return Objects.equal(tagsCount, tagDto.tagsCount) && Objects.equal(id, tagDto.id) && Objects.equal(name, tagDto.name) && Objects.equal(slug, tagDto.slug) && Objects.equal(description, tagDto.description) && Objects.equal(icon, tagDto.icon) && Objects.equal(color, tagDto.color) && Objects.equal(state, tagDto.state);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), tagsCount, id, name, slug, description, icon, color, state);
	}
}