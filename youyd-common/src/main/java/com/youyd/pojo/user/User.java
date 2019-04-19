package com.youyd.pojo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户实体类
 * @author: LGG
 * @create: 2018-09-27
 **/
@ApiModel(value="user", description="用户实体类")
@Getter
@Setter
public class User extends BasePojo implements Serializable{

	@TableId(type = IdType.ID_WORKER)
	private Long id;
	private String account; // 账号
	private String userName; //用户名
	private String password; //密码
	private String sex;//性别
	private Date birthday;//出生年月日
	private String avatar;//头像
	private String email;//E-Mail
	private Date lastDate;//最后登陆日期
	private Long onlineTime;//在线时长（分钟）
	private String interest;//兴趣
	private String personality;//个性
	private Integer fansCount;//粉丝数
	private Integer followCount;//关注数
	private String mobile;//手机
	private String contactAddress;//联系地址
	private String registeredType;//注册类型/方式
	private Integer status;//是否锁定(0:未锁定,1已锁定)

	private static final long serialVersionUID = 1L;
}