package com.madao.auth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Oauth临时存储user类
 **/
public class UserJwt extends User {

    private String id;
    private String name;
    private String account;
    private String nickName;
    private String avatar;
    private String phone;
    private String email;

    public UserJwt(String username, String password, String id, String nickName,
                   String avatar, String email, String phone, String account,
                   Collection<GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.nickName = nickName;
        this.account = account;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
    }

	public String getId() {
		return id;
	}

	public UserJwt setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public UserJwt setName(String name) {
		this.name = name;
		return this;
	}

	public String getAccount() {
		return account;
	}

	public UserJwt setAccount(String account) {
		this.account = account;
		return this;
	}

	public String getNickName() {
		return nickName;
	}

	public UserJwt setNickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public String getAvatar() {
		return avatar;
	}

	public UserJwt setAvatar(String avatar) {
		this.avatar = avatar;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public UserJwt setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserJwt setEmail(String email) {
		this.email = email;
		return this;
	}
}
