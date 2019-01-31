package com.youyd.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;

import java.io.Serializable;

/**
 * @description: 菜单实体
 * @author: LGG
 * @create: 2018-09-27
 **/
public class Menu extends BasePojo implements Serializable {

	@TableId(type = IdType.ID_WORKER)
	private Long id; // 菜单表主键

	private String title; // 标题

	private String name; // 名称

	private String parentId; // 父资源id

	private String component; // vue组件名称/名称必须真实存在

	private String icon; // 图标

	private String path; // 路径

	private Long state; // 状态（禁用：0，启用：1）

	private Long sort; // 排序

	private String description; // 描述

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}