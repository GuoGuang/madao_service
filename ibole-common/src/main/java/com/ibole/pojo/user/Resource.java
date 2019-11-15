package com.ibole.pojo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ibole.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

/**
 *  资源实体
 **/
@Getter
@Setter
public class Resource extends BasePojo implements Serializable,Cloneable {

	@TableId(type = IdType.ID_WORKER_STR)
	private String id; // 资源表主键

	@NotNull(message="名称不能为空")
	@Pattern(regexp="([a-zA-Z0-9\u4E00-\u9FA5]{2,10})",message="必须是2到10位(字母，数字)名称！")
	private String name; // 名称

	private String parentId; // 父资源id

	private String component; // vue组件名称/名称必须真实存在

	private String icon; // 图标

	private String path; // 路径

	private float sort; // 排序

	private Integer isHidden; // 是否隐藏

	@NotNull(message="描述不能为空")
	private String description; // 描述

	private String type; // btn 或resource
	private String url; // 请求url，跟path不同，path为vue的组件请求路径，url是网络请求路径
	private String method;  // 请求方法
	private String code;  // 资源标识

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Resource resource = (Resource) o;
		return id.equals(resource.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}