package com.youyd.pojo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 *  菜单实体
 * @author LGG
 * @create 2018-09-27
 **/
@Getter
@Setter
public class Menu extends BasePojo implements Serializable {

	@TableId(type = IdType.ID_WORKER_STR)
	private String id; // 菜单表主键

	@NotNull(message="名称不能为空")
	@Pattern(regexp="([a-zA-Z0-9\u4E00-\u9FA5]{2,10})",message="必须是2到10位(字母，数字)名称！")
	private String name; // 名称

	private String parentId; // 父资源id

	private String component; // vue组件名称/名称必须真实存在

	private String icon; // 图标

	private String path; // 路径

	private float sort; // 排序

	@NotNull(message="描述不能为空")
	private String description; // 描述

}