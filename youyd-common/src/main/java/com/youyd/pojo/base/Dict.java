package com.youyd.pojo.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * @description: 字典表
 * @author LGG
 * @create 2019-05-08
 **/
@Getter
@Setter
public class Dict extends BasePojo implements Serializable {


    @TableId(type = IdType.ID_WORKER_STR)
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