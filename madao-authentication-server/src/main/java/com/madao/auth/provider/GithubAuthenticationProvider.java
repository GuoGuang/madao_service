package com.madao.auth.provider;

import com.madao.auth.service.GitHubDetailsServiceImpl;
import com.madao.auth.token.CaptchaAuthenticationToken;
import com.madao.auth.token.GitHubAuthenticationToken;
import com.madao.utils.HttpHelper;
import com.madao.utils.JsonUtil;
import com.madao.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 三方GitHub登录
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Component
public class GithubAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Autowired
	@Qualifier("gitHubDetailsServiceImpl")
	private GitHubDetailsServiceImpl gitHubDetailsServiceImpl;

	@Override
	public Authentication authenticate(Authentication authentication) {

		GitHubAuthenticationToken authenticationToken = (GitHubAuthenticationToken) authentication;
		String accessToken = authenticationToken.getPrincipal().toString();
		Map<String, Object> userInfo = HttpHelper.httpOauthGet(accessToken);

		System.out.println(userInfo);
		UserDetails userDetails = gitHubDetailsServiceImpl.loadUserByUsername(JsonUtil.toJsonString(userInfo));
		return new CaptchaAuthenticationToken(userDetails, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (GitHubAuthenticationToken.class.isAssignableFrom(authentication));
	}

}