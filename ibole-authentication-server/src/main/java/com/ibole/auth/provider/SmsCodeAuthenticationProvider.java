package com.ibole.auth.provider;

import com.ibole.auth.service.CustomUserDetailsService;
import com.ibole.auth.token.SmsCodeAuthenticationToken;
import com.ibole.auth.exception.ValidateCodeException;
import com.ibole.pojo.user.User;
import com.ibole.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 短信登录验证
 * 由于短信验证码的验证在第一个过滤器里已完成，这里直接读取用户信息即可。
 **/
@Component
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
		String phone = (String) authenticationToken.getPrincipal();
		User user = new User();
		user.setPhone(phone);
		UserDetails userInfo = userDetailsService.loadUserByUsername(JsonUtil.toJsonString(user));
		if (userInfo == null) {
			throw new ValidateCodeException("手机号不存在！");
		}
		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userInfo, userInfo.getAuthorities());

		authenticationResult.setDetails(authenticationToken.getDetails());

		return authenticationResult;
	}


	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}
}