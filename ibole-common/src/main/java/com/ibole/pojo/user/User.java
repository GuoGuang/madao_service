package com.ibole.pojo.user;

import com.ibole.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * 用户实体类
 **/
@ApiModel(value = "user", description = "用户实体类")
@Getter
@Setter
@Entity
@Table(name = "us_user", schema = "test", catalog = "")
public class User extends BasePojo implements Serializable {

    @Transient
    private List<Role> roles;

    @Transient
    private List<Resource> resource;

    @Transient
    private String captcha;

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
    private String id;

    @NotNull(message = "账号不能为空")
    private String account; // 账号

    @NotNull(message = "用户名不能为空")
    private String userName; // 用户名

    @NotNull(message = "昵称不能为空")
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