package com.codeif.pojo.user;

import com.codeif.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@ApiModel(value = "user", description = "用户实体类")
@Getter
@Setter
@Entity
@Table(name = "us_user")
public class User extends BasePojo implements Serializable {

	@ApiModelProperty("角色集合")
	@JoinTable(name = "us_user_role",
			joinColumns = @JoinColumn(name = "user_id",referencedColumnName="id",foreignKey=@ForeignKey(name="null") ),
			inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName="id",foreignKey=@ForeignKey(name="null")))
	@ManyToMany
	private Set<Role> roles = new HashSet<>();


	@ApiModelProperty("验证码")
	@Transient
	private String captcha;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.codeif.config.IdGeneratorConfig")
	@ApiModelProperty("用户表主键")
	@Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
	private String id;

	@NotNull(message = "账号不能为空")
	@ApiModelProperty("账号")
	@Column(length = 20)
	private String account;

	@NotNull(message = "用户名不能为空")
	@ApiModelProperty("用户名")
	@Column(length = 40)
	private String userName;

	@NotNull(message = "昵称不能为空")
	@ApiModelProperty("昵称")
	@Column(length = 80)
	private String nickName;

	@NotNull(message = "密码不能为空")
	@ApiModelProperty("密码")
	@Column(length = 100)
	private String password;

	@NotNull(message = "性别不能为空")
	@ApiModelProperty("性别")
	@Column(length = 1)
	private int sex;

	@ApiModelProperty(value = "出生年月日",example = "1")
	@Column(length = 13)
	private Long birthday;

	@ApiModelProperty("头像")
	@Column(length = 300)
	private String avatar;

	@NotNull(message = "E-Mail不能为空")
	@Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确!")
	@ApiModelProperty("E-Mail")
	@Column(length = 30)
	private String email;

	@ApiModelProperty(value = "最后登陆日期",example = "1")
	@Column(length = 13)
	private Long lastDate;

	@ApiModelProperty(value = "在线时长（分钟）",example = "1")
	@Column(length = 13)
	private Long onlineTime;

	@ApiModelProperty("兴趣")
	@Column(length = 30)
	private String interest;

	@ApiModelProperty("个性")
	@Column(length = 20)
	private String personality;

	@ApiModelProperty(value = "粉丝数",example = "123")
	@Column(length = 10)
	private Integer fansCount;

	@ApiModelProperty(value = "关注数",example = "123")
	@Column(length = 10)
	private Integer followCount;

	@ApiModelProperty("手机")
	@NotNull(message = "手机不能为空")
	@Column(length = 15)
	private String phone;

	@ApiModelProperty("联系地址")
	@Column(length = 200)
	private String contactAddress;

	@ApiModelProperty("注册类型/方式")
	@Column(length = 10)
	private String registeredType;

	@ApiModelProperty(value = "是否锁定(0:未锁定,1已锁定)",example = "0")
	@Column(length = 1)
	private Integer status;

	@Override
	public String toString() {
		return "User{" +
				"captcha='" + captcha + '\'' +
				", id='" + id + '\'' +
				", account='" + account + '\'' +
				", userName='" + userName + '\'' +
				", nickName='" + nickName + '\'' +
				", password='" + password + '\'' +
				", sex=" + sex +
				", birthday=" + birthday +
				", avatar='" + avatar + '\'' +
				", email='" + email + '\'' +
				", lastDate=" + lastDate +
				", onlineTime=" + onlineTime +
				", interest='" + interest + '\'' +
				", personality='" + personality + '\'' +
				", fansCount=" + fansCount +
				", followCount=" + followCount +
				", phone='" + phone + '\'' +
				", contactAddress='" + contactAddress + '\'' +
				", registeredType='" + registeredType + '\'' +
				", status=" + status +
				'}';
	}
}