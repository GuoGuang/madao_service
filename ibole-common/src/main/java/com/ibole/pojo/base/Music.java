package com.ibole.pojo.base;

import com.ibole.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 音乐实体
 **/

@Getter
@Setter
//@Entity
//@Table(name = "")
public class Music extends BasePojo implements Serializable {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 父id
     */
    private String parentId;

    @NotNull(message="编码不能为空")
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