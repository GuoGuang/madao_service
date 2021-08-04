package com.madao.model.entity.user;

import com.madao.enums.ProviderEnum;
import com.madao.model.BasePojo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Entity
@Table(name = "us_user",
        indexes = {
                @Index(name = "user_user_name", columnList = "userName"),
                @Index(name = "user_account", columnList = "account"),
                @Index(name = "user_email", columnList = "email"),
                @Index(name = "user_status", columnList = "status"),
                @Index(name = "user_phone", columnList = "phone"),
                @Index(name = "user_create_at", columnList = "createAt")
        })
public class User extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
    private String id;

    @Column(length = 20)
    private String account;

    @Column(length = 40)
    private String userName;

    @Column(length = 80)
    private String nickName;

    @Column(length = 100)
    private String password;

    @Column(length = 1)
    private Boolean sex;

    @Column(length = 13)
    private Long birthday;

    @Column(length = 300)
    private String avatar;

    @Column(length = 30)
    private String email;

    @Column(length = 13)
    private Long lastDate;

    @Column(length = 13)
    private Long onlineTime;

    /**
     * 兴趣
     */
    @Column(length = 30)
    private String interest;

    /**
     * 个性
     */
    @Column(length = 20)
    private String personality;

    @Column(length = 10)
    private Integer fansCount;

    @Column(length = 10)
    private Integer followCount;

    @Column(length = 15)
    private String phone;

    @Column(length = 200)
    private String contactAddress;

    /**
     * 是否锁定
     */
    @Column(length = 1)
    private Boolean status;

    @Column(length = 1)
    private Boolean origin;

    @Column(length = 20)
    private String bindId;

    @Column(length = 1)
    private ProviderEnum provider;

	public String getId() {
		return id;
	}

	public User setId(String id) {
		this.id = id;
		return this;
	}

	public String getAccount() {
		return account;
	}

	public User setAccount(String account) {
		this.account = account;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public User setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getNickName() {
		return nickName;
	}

	public User setNickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public Boolean getSex() {
		return sex;
	}

	public User setSex(Boolean sex) {
		this.sex = sex;
		return this;
	}

	public Long getBirthday() {
		return birthday;
	}

	public User setBirthday(Long birthday) {
		this.birthday = birthday;
		return this;
	}

	public String getAvatar() {
		return avatar;
	}

	public User setAvatar(String avatar) {
		this.avatar = avatar;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public Long getLastDate() {
		return lastDate;
	}

	public User setLastDate(Long lastDate) {
		this.lastDate = lastDate;
		return this;
	}

	public Long getOnlineTime() {
		return onlineTime;
	}

	public User setOnlineTime(Long onlineTime) {
		this.onlineTime = onlineTime;
		return this;
	}

	public String getInterest() {
		return interest;
	}

	public User setInterest(String interest) {
		this.interest = interest;
		return this;
	}

	public String getPersonality() {
		return personality;
	}

	public User setPersonality(String personality) {
		this.personality = personality;
		return this;
	}

	public Integer getFansCount() {
		return fansCount;
	}

	public User setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
		return this;
	}

	public Integer getFollowCount() {
		return followCount;
	}

	public User setFollowCount(Integer followCount) {
		this.followCount = followCount;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public User setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public User setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
		return this;
	}

	public Boolean getStatus() {
		return status;
	}

	public User setStatus(Boolean status) {
		this.status = status;
		return this;
	}

	public Boolean getOrigin() {
		return origin;
	}

	public User setOrigin(Boolean origin) {
		this.origin = origin;
		return this;
	}

	public String getBindId() {
		return bindId;
	}

	public User setBindId(String bindId) {
		this.bindId = bindId;
		return this;
	}

	public ProviderEnum getProvider() {
		return provider;
	}

	public User setProvider(ProviderEnum provider) {
		this.provider = provider;
		return this;
	}
}