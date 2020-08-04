package com.codeway.pojo.base;

import com.codeway.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * 音乐实体
 **/

@Getter
@Setter
//@Entity
//@Table(name = "")
public class Music extends BasePojo implements Serializable {

    //  @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
    private String id;

    /**
     * 父id
     */
    private String parentId;

    @ApiModelProperty(value = "编码",example = "1")
    @NotNull(message = "编码不能为空")
    private String code;

    @ApiModelProperty("名称")
    @NotNull(message = "编码不能为空")
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Music)) return false;
		if (!super.equals(o)) return false;
		Music music = (Music) o;
		return id.equals(music.id) &&
				Objects.equals(parentId, music.parentId) &&
				Objects.equals(code, music.code) &&
				Objects.equals(name, music.name) &&
				Objects.equals(description, music.description) &&
				Objects.equals(state, music.state) &&
				Objects.equals(type, music.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, parentId, code, name, description, state, type);
	}
}