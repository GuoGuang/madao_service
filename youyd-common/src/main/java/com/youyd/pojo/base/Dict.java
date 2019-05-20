package com.youyd.pojo.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * @description: 字典表
 * @author: LGG
 * @create: 2019-05-08
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

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 类型
     */
    private String type;

    private static final long serialVersionUID = 1L;

}