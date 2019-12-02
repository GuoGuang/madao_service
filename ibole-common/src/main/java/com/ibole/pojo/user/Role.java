package com.ibole.pojo.user;

import com.ibole.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 角色实体
 **/

@Getter
@Setter
@Entity
@Table(name = "us_role")
public class Role extends BasePojo implements Serializable {

    @Transient
    private List<Resource> resource; // 角色关联的资源

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
    private String id; // 角色表主键

    @NotNull(message = "角色名称不能为空")
    private String roleName; // 角色名称

    private String roleDesc; // 角色描述

    @NotNull(message = "角色编码不能为空")
    private String roleCode; // 角色编码

}