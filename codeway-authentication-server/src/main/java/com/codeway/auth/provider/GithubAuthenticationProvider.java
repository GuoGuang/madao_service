package com.codeway.auth.provider;

import com.codeway.auth.service.GitHubDetailsServiceImpl;
import com.codeway.auth.token.CaptchaAuthenticationToken;
import com.codeway.auth.token.GitHubAuthenticationToken;
import com.codeway.db.redis.service.RedisService;
import com.codeway.utils.HttpHelper;
import com.codeway.utils.JsonUtil;
import lombok.Data;
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
 **/
@Data
@Component
public class GithubAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private RedisService redisService;

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