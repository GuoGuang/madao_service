package com.madao.model.dto.base;

import com.google.common.base.Objects;
import com.madao.model.BasePojo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class MusicDto extends BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String parentId;
	@Schema(title = "编码", example = "1")
	@NotNull(message = "编码不能为空")
	private String code;
	@Schema(title = "名称")
	@NotNull(message = "编码不能为空")
	private String name;
	@Schema(title = "描述")
	@NotNull(message = "描述不能为空")
	private String description;
	@Schema(title = "状态")
	private Integer state;
	@Schema(title = "类型")
	@NotNull(message = "类型不能为空")
	private String type;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MusicDto musicDto)) return false;
		if (!super.equals(o)) return false;
		return Objects.equal(id, musicDto.id) && Objects.equal(parentId, musicDto.parentId) && Objects.equal(code, musicDto.code) && Objects.equal(name, musicDto.name) && Objects.equal(description, musicDto.description) && Objects.equal(state, musicDto.state) && Objects.equal(type, musicDto.type);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), id, parentId, code, name, description, state, type);
	}
}