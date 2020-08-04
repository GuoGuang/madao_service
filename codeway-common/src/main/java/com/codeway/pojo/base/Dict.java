package com.codeway.pojo.base;

import com.codeway.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "ba_dict")
public class Dict extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
    @ApiModelProperty("字典表表主键")
    @Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
    private String id;

    @ApiModelProperty("父id")
    @Column(length = 20)
    private String parentId;

    @ApiModelProperty("编码")
    @NotNull(message = "编码不能为空")
    @Column(length = 10)
    private String code;

    @ApiModelProperty("名称")
    @NotNull(message = "名称不能为空")
    @Column(length = 10)
    private String name;

    @ApiModelProperty("描述")
    @NotNull(message = "描述不能为空")
    @Column(length = 200)
    private String description;

    @ApiModelProperty(value = "状态",example = "1")
    @Column(length = 1)
    private Integer state;

	@ApiModelProperty("类型")
	@NotNull(message = "类型不能为空")
	@Column(length = 10)
	private String type;

	private static final long serialVersionUID = 1L;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Dict)) return false;
		if (!super.equals(o)) return false;
		Dict dict = (Dict) o;
		return id.equals(dict.id) &&
				Objects.equals(parentId, dict.parentId) &&
				Objects.equals(code, dict.code) &&
				Objects.equals(name, dict.name) &&
				Objects.equals(description, dict.description) &&
				Objects.equals(state, dict.state) &&
				Objects.equals(type, dict.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, parentId, code, name, description, state, type);
	}
}