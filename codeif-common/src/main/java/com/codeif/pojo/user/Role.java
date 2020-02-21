package com.codeif.pojo.user;

import com.codeif.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "us_role")
public class Role extends BasePojo implements Serializable {

    @Transient
    @ApiModelProperty("角色关联的资源")
    private List<Resource> resource;

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeif.config.IdGeneratorConfig")
    @ApiModelProperty("角色表主键")
    private String id;

    @NotNull(message = "角色名称不能为空")
    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色描述")
    private String roleDesc;

    @NotNull(message = "角色编码不能为空")
    @ApiModelProperty("角色编码")
    private String roleCode;

}