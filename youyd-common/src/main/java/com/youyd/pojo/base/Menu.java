package com.youyd.pojo.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 菜单实体
 * @author: LGG
 * @create: 2018-09-27
 **/
@Data
public class Menu extends BasePojo implements Serializable {

	@TableId(type = IdType.ID_WORKER)
	private Long id; // 菜单表主键

	private String name; // 名称

	private String parentId; // 父资源id

	private String component; // vue组件名称/名称必须真实存在

	private String icon; // 图标

	private String path; // 路径

	private Long status; // 状态（禁用：0，启用：1）

	private Long sort; // 排序

	private String description; // 描述

}