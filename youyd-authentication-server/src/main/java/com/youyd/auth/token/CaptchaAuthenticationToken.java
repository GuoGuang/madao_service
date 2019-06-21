package com.youyd.auth.token;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


/**
 * 图片登录验证信息封装类
 * @author : LGG
 * @create : 2019-06-18 14:34
 **/
@Data
public class CaptchaAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private String captcha;    //验证码
	private String captchaUuid;    //验证码uuid

	//未认证Authentication构造方法
	public CaptchaAuthenticationToken(String captcha,String captchaUuid,Object principal, Object credentials) {
		super(principal,credentials);
		this.captcha = captcha;
		this.captchaUuid = captchaUuid;
		setAuthenticated(false);
	}

	//已认证Authentication构造方法
	public CaptchaAuthenticationToken(String captcha,Object principal, Collection<? extends GrantedAuthority> authorities) {
		super(principal,"");
		this.captcha = captcha;
		setAuthenticated(true);
	}

	public CaptchaAuthenticationToken(Object principal,Collection<? extends GrantedAuthority> authorities) {
		super(principal,authorities);
	}

}

