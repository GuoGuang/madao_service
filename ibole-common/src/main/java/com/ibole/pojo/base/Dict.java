package com.ibole.pojo.base;

import com.ibole.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 字典表
 **/

@Getter
@Setter
@ToString
@Entity
@Table(name = "ba_dict")
public class Dict extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
    private String id;

    /**
     * 父id
     */
    private String parentId;

    @NotNull(message = "编码不能为空")
    private String code; // 编码

    @NotNull(message="编码不能为空")
    private String name; // 名称

    @NotNull(message="描述不能为空")
    private String description; // 描述

    private Integer state; // 状态

    @NotNull(message="类型不能为空")
    private String type; // 类型

    private static final long serialVersionUID = 1L;

}