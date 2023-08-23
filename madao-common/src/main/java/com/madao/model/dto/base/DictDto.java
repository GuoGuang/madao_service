package com.madao.model.dto.base;

import com.madao.model.BasePojo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class DictDto extends BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Schema(title = "字典表表主键")
	private String id;
	@Schema(title = "父id")
	private String parentId;
	@Schema(title = "编码")
	@NotNull(message = "编码不能为空")
	private String code;
	@Schema(title = "名称")
	@NotNull(message = "名称不能为空")
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
		if (!(o instanceof DictDto dictDto)) return false;
		if (!super.equals(o)) return false;
		return Objects.equals(id, dictDto.id) && Objects.equals(parentId, dictDto.parentId) && Objects.equals(code, dictDto.code) && Objects.equals(name, dictDto.name) && Objects.equals(description, dictDto.description) && Objects.equals(state, dictDto.state) && Objects.equals(type, dictDto.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, parentId, code, name, description, state, type);
	}
}