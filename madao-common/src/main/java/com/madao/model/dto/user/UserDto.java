package com.madao.model.dto.user;

import com.madao.enums.ProviderEnum;
import com.madao.model.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@ApiModel(value = "user", description = "用户实体类")
public class UserDto extends BasePojo implements Serializable {

    @ApiModelProperty("角色集合")
    private Set<com.madao.model.dto.user.RoleDto> roles = new HashSet<>();

    @ApiModelProperty("验证码")
    @NotNull(groups = {UserDto.Register.class})
    private String captcha;

    @ApiModelProperty("用户表主键")
    private String id;

    @NotNull(message = "账号不能为空")
    @ApiModelProperty("账号")
    private String account;

    @NotNull(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String userName;

    @NotNull(message = "昵称不能为空", groups = {UserDto.ChangeUserInfo.class})
    @ApiModelProperty("昵称")
    private String nickName;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;

    @NotNull(message = "性别不能为空")
    @ApiModelProperty("性别")
    private Boolean sex;

    @ApiModelProperty(value = "出生年月日")
    private Long birthday;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("E-Mail")
    @NotNull(message = "E-Mail不能为空")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确!")
    private String email;

    @ApiModelProperty(value = "最后登陆日期")
    private Long lastDate;

    @ApiModelProperty(value = "在线时长（分钟）")
    private Long onlineTime;

    @ApiModelProperty("兴趣")
    private String interest;

    @ApiModelProperty("个性")
    private String personality;

    @ApiModelProperty(value = "粉丝数", example = "123")
    private Integer fansCount;

    @ApiModelProperty(value = "关注数", example = "123")
    private Integer followCount;

    @ApiModelProperty("手机")
    @NotNull(message = "手机不能为空", groups = {UserDto.Register.class})
    private String phone;

    @ApiModelProperty("联系地址")
    private String contactAddress;

    @ApiModelProperty(value = "是否锁定(0:未锁定,1已锁定)", example = "0")
    private Boolean status;

    @ApiModelProperty(value = "1前台用户，0后台用户", example = "0")
    private Boolean origin;

    @ApiModelProperty(value = "三方登录id", example = "0")
    private String bindId;

    @ApiModelProperty(value = "三方登录提供商", example = "1")
    private ProviderEnum provider;

    public UserDto(String userId) {
        this.id = userId;
    }
    public UserDto() {
    }

    public interface Register {
    }

    public interface ChangeUserInfo {
    }

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public UserDto setRoles(Set<RoleDto> roles) {
		this.roles = roles;
		return this;
	}

	public String getCaptcha() {
		return captcha;
	}

	public UserDto setCaptcha(String captcha) {
		this.captcha = captcha;
		return this;
	}

	public String getId() {
		return id;
	}

	public UserDto setId(String id) {
		this.id = id;
		return this;
	}

	public String getAccount() {
		return account;
	}

	public UserDto setAccount(String account) {
		this.account = account;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public UserDto setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getNickName() {
		return nickName;
	}

	public UserDto setNickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserDto setPassword(String password) {
		this.password = password;
		return this;
	}

	public Boolean getSex() {
		return sex;
	}

	public UserDto setSex(Boolean sex) {
		this.sex = sex;
		return this;
	}

	public Long getBirthday() {
		return birthday;
	}

	public UserDto setBirthday(Long birthday) {
		this.birthday = birthday;
		return this;
	}

	public String getAvatar() {
		return avatar;
	}

	public UserDto setAvatar(String avatar) {
		this.avatar = avatar;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserDto setEmail(String email) {
		this.email = email;
		return this;
	}

	public Long getLastDate() {
		return lastDate;
	}

	public UserDto setLastDate(Long lastDate) {
		this.lastDate = lastDate;
		return this;
	}

	public Long getOnlineTime() {
		return onlineTime;
	}

	public UserDto setOnlineTime(Long onlineTime) {
		this.onlineTime = onlineTime;
		return this;
	}

	public String getInterest() {
		return interest;
	}

	public UserDto setInterest(String interest) {
		this.interest = interest;
		return this;
	}

	public String getPersonality() {
		return personality;
	}

	public UserDto setPersonality(String personality) {
		this.personality = personality;
		return this;
	}

	public Integer getFansCount() {
		return fansCount;
	}

	public UserDto setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
		return this;
	}

	public Integer getFollowCount() {
		return followCount;
	}

	public UserDto setFollowCount(Integer followCount) {
		this.followCount = followCount;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public UserDto setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public UserDto setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
		return this;
	}

	public Boolean getStatus() {
		return status;
	}

	public UserDto setStatus(Boolean status) {
		this.status = status;
		return this;
	}

	public Boolean getOrigin() {
		return origin;
	}

	public UserDto setOrigin(Boolean origin) {
		this.origin = origin;
		return this;
	}

	public String getBindId() {
		return bindId;
	}

	public UserDto setBindId(String bindId) {
		this.bindId = bindId;
		return this;
	}

	public ProviderEnum getProvider() {
		return provider;
	}

	public UserDto setProvider(ProviderEnum provider) {
		this.provider = provider;
		return this;
	}
}