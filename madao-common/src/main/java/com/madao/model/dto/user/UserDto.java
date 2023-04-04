package com.madao.model.dto.user;

import com.madao.enums.ProviderEnum;
import com.madao.model.BasePojo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Schema(title = "user", description = "用户实体类")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserDto extends BasePojo implements Serializable {

	@Schema(title = "角色集合")
	private Set<RoleDto> roles = new HashSet<>();

	@Schema(title = "验证码")
	@NotNull(groups = {UserDto.Register.class})
	private String captcha;

	@Schema(title = "用户表主键")
	private String id;

	@NotNull(message = "账号不能为空")
	@Schema(title = "账号")
	private String account;

	@NotNull(message = "用户名不能为空")
	@Schema(title = "用户名")
	private String userName;

	@NotNull(message = "昵称不能为空", groups = {UserDto.ChangeUserInfo.class})
	@Schema(title = "昵称")
	private String nickName;

	@NotNull(message = "密码不能为空")
	@Schema(title = "密码")
	private String password;

	@NotNull(message = "性别不能为空")
	@Schema(title = "性别")
	private Boolean sex;

	@Schema(title = "出生年月日")
	private Long birthday;

	@Schema(title = "头像")
	private String avatar;

	@Schema(title = "E-Mail")
	@NotNull(message = "E-Mail不能为空")
	@Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确!")
	private String email;

	@Schema(title = "最后登陆日期")
	private Long lastDate;

	@Schema(title = "在线时长（分钟）")
	private Long onlineTime;

	@Schema(title = "兴趣")
	private String interest;

	@Schema(title = "个性")
	private String personality;

	@Schema(title = "粉丝数", example = "123")
	private Integer fansCount;

	@Schema(title = "关注数", example = "123")
	private Integer followCount;

	@Schema(title = "手机")
	@NotNull(message = "手机不能为空", groups = {UserDto.Register.class})
	private String phone;

	@Schema(title = "联系地址")
	private String contactAddress;

	@Schema(title = "是否锁定(0:未锁定,1已锁定)", example = "0")
	private Boolean status;

	@Schema(title = "1前台用户，0后台用户", example = "0")
	private Boolean origin;

	@Schema(title = "三方登录id", example = "0")
	private String bindId;

	@Schema(title = "三方登录提供商", example = "1")
	private ProviderEnum provider;

	public UserDto(String userId) {
		this.id = userId;
	}

	public interface Register {
	}

	public interface ChangeUserInfo {
	}

}