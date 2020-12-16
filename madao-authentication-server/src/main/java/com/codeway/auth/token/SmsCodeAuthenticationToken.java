package com.madaoo.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * 短信登录验证信息封装类
 * @author LGG
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	// 认证之前是手机号，认证之后存登录成功的用户或者其他信息
	private final Object principal;

	/**
	 * 认证之前的构造函数
	 */
	public SmsCodeAuthenticationToken(String phone) {
		super(null);
		this.principal = phone;
		setAuthenticated(false);
	}

	/**
	 * 认证之后的构造函数
	 * @param principal
	 * @param authorities
	 */
	public SmsCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		// must use super, as we override
		super.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}
}
