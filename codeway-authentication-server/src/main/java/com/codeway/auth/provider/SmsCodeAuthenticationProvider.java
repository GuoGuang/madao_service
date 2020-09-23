package com.codeway.auth.provider;

import com.codeway.auth.exception.ValidateCodeException;
import com.codeway.auth.service.UserDetailsServiceImpl;
import com.codeway.auth.token.SmsCodeAuthenticationToken;
import com.codeway.model.pojo.user.User;
import com.codeway.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 短信登录验证
 * 由于短信验证码的验证在第一个过滤器里已完成，这里直接读取用户信息即可。
 **/
@Component
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	public Authentication authenticate(Authentication authentication) {

		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
		String phone = (String) authenticationToken.getPrincipal();
		User user = new User();
		user.setPhone(phone);
		UserDetails userInfo = userDetailsServiceImpl.loadUserByUsername(JsonUtil.toJsonString(user));
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