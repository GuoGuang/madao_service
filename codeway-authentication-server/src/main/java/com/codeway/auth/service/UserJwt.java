package com.madaoo.auth.service;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
/**
 * Oauth临时存储user类
 **/
@Data
@ToString
public class UserJwt extends User {

    private String id;
    private String name;
    private String account;
    private String nickName;
    private String avatar;
    private String phone;
    private String email;

	public UserJwt(String username, String password, String id, String nickName,
	               String avatar, String email, String phone,String account,
	               Collection<GrantedAuthority> authorities) {
		super(username, password, authorities);
    	this.id = id;
    	this.nickName = nickName;
    	this.account = account;
    	this.avatar = avatar;
    	this.email = email;
    	this.phone = phone;
	}
}
