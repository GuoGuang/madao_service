package com.codeway.pojo.base;

import com.codeway.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "ba_dict",
		indexes = {
				@Index(name = "dict_code", columnList = "code"),
				@Index(name = "dict_parent_id", columnList = "parentId"),
				@Index(name = "dict_create_at", columnList = "createAt")
		})
@EqualsAndHashCode(callSuper = true)
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

	@ApiModelProperty("类型")
	@NotNull(message = "类型不能为空")
	@Column(length = 10)
	private String type;

	private static final long serialVersionUID = 1L;

}