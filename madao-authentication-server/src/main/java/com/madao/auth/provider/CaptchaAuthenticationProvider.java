package com.madao.auth.provider;

import com.madao.auth.exception.AuthException;
import com.madao.auth.service.UserDetailsServiceImpl;
import com.madao.auth.service.UserJwt;
import com.madao.auth.token.CaptchaAuthenticationToken;
import com.madao.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 图片验证码登录验证
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Component
public class CaptchaAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("userDetailsServiceImpl")
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	public Authentication authenticate(Authentication authentication) {

		CaptchaAuthenticationToken authenticationToken = (CaptchaAuthenticationToken) authentication;
		String account = authenticationToken.getPrincipal().toString();
		UserJwt userInfo = (UserJwt) userDetailsServiceImpl.loadUserByUsername(account);
		if (userInfo == null) {
			throw new AuthException(StatusEnum.LOGIN_ERROR.getMsg());
		}
		String password = userInfo.getPassword();
		String credentials = authentication.getCredentials().toString();
		if (!passwordEncoder.matches(credentials, password)) {
			throw new AuthException(StatusEnum.LOGIN_ERROR.getMsg());
		}
		CaptchaAuthenticationToken authenticationResult = new CaptchaAuthenticationToken(userInfo.getId(), userInfo.getAuthorities());
		authenticationResult.setDetails(userInfo);
		return authenticationResult;
	}


	/**
	 * supports方法用于检查入参的类型，AuthenticationProvider只会认证符合条件的类型
	 * 检查入参Authentication是否是UsernamePasswordAuthenticationToken或它的子类
	 * <p>
	 * 系统默认的Authentication入参都是UsernamePasswordAuthenticationToken类型，所以这里supports必须为true。
	 * 需自定义认证过滤器，到时候就可以自定义不同的入参类型了，以适用于不同的AuthenticationProvider。
	 *
	 * @param authentication 符合条件的类型
	 * @return boolean
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return (CaptchaAuthenticationToken.class.isAssignableFrom(authentication));
	}

}