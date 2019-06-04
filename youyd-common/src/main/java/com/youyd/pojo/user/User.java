package com.youyd.pojo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 用户实体类
 * @author: LGG
 * @create: 2018-09-27
 **/
@ApiModel(value="user", description="用户实体类")
@Getter
@Setter
public class User extends BasePojo implements Serializable{

	@TableField(exist = false)
	private List<Role> roles;
	@TableField(exist = false)
	private List<Menu> menus;


	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	@NotNull(message="账号不能为空")
	private String account; // 账号

	@NotNull(message="用户名不能为空")
	private String userName; // 用户名

	@NotNull(message="昵称不能为空")
	private String nickName; // 昵称

	@NotNull(message="密码不能为空")
	private String password; // 密码

	@NotNull(message="性别不能为空")
	private String sex; // 性别
	private Long birthday;//出生年月日
	private String avatar;//头像

	@NotNull(message="E-Mail不能为空")
	@Pattern(regexp="^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message="邮箱格式不正确!")
	private String email;//E-Mail

	private Long lastDate;//最后登陆日期
	private Long onlineTime;//在线时长（分钟）
	private String interest;//兴趣
	private String personality;//个性
	private Integer fansCount;//粉丝数
	private Integer followCount;//关注数

	@NotNull(message="手机不能为空")
	private String phone;//手机
	private String contactAddress;//联系地址
	private String registeredType;//注册类型/方式
	private Integer status;//是否锁定(0:未锁定,1已锁定)

}