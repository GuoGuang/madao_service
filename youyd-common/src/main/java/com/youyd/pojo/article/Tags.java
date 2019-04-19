package com.youyd.pojo.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel(value="article", description="标签类")
public class Tags extends BasePojo implements Serializable {


	/**
	 * 接收连接查询结果专用字段
	 */
	@TableField(exist=false)
	private Integer tagsCount;

    /**
     * ID
     */
    @TableId(type = IdType.ID_WORKER)
    private Integer id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String slug;

    /**
     * 描述
     */
    private String description;

    /**
     * 标签图标
     */
    private String icon;

    /**
     * 状态
     */
    private String state;


}