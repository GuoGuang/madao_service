package com.madao.model.dto.base;

import com.madao.model.BasePojo;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class DictDto extends BasePojo implements Serializable {

    @ApiModelProperty("字典表表主键")
    private String id;

    @ApiModelProperty("父id")
    private String parentId;

    @ApiModelProperty("编码")
    @NotNull(message = "编码不能为空")
    private String code;

    @ApiModelProperty("名称")
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty("描述")
    @NotNull(message = "描述不能为空")
    private String description;

    @ApiModelProperty(value = "状态", example = "1")
    private Integer state;

    @ApiModelProperty("类型")
    @NotNull(message = "类型不能为空")
    private String type;

    private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

	public DictDto setId(String id) {
		this.id = id;
		return this;
	}

	public String getParentId() {
		return parentId;
	}

	public DictDto setParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getCode() {
		return code;
	}

	public DictDto setCode(String code) {
		this.code = code;
		return this;
	}

	public String getName() {
		return name;
	}

	public DictDto setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public DictDto setDescription(String description) {
		this.description = description;
		return this;
	}

	public Integer getState() {
		return state;
	}

	public DictDto setState(Integer state) {
		this.state = state;
		return this;
	}

	public String getType() {
		return type;
	}

	public DictDto setType(String type) {
		this.type = type;
		return this;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof DictDto)) return false;
		if (!super.equals(o)) return false;
		DictDto dictDto = (DictDto) o;
		return Objects.equals(id, dictDto.id) && Objects.equals(parentId, dictDto.parentId) && Objects.equals(code, dictDto.code) && Objects.equals(name, dictDto.name) && Objects.equals(description, dictDto.description) && Objects.equals(state, dictDto.state) && Objects.equals(type, dictDto.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, parentId, code, name, description, state, type);
	}
}