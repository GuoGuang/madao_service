package com.madao.auth.filter;

import com.madao.auth.exception.AuthException;
import com.madao.auth.token.GitHubAuthenticationToken;
import com.madao.utils.HttpHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * GitHub登录
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
public class GithubAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	private OAuth2ClientProperties oAuth2ClientProperties;

	public GithubAuthenticationFilter() {
		super(new AntPathRequestMatcher("/auth/login/github", "GET"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String code = request.getParameter("code");
		OAuth2ClientProperties.Registration github = oAuth2ClientProperties.getRegistration().get("github");

		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("client_id", github.getClientId()));
		params.add(new BasicNameValuePair("client_secret", github.getClientSecret()));
		params.add(new BasicNameValuePair("code", code));
		Map<String, String> responseBody = HttpHelper.httpPost(params);
		if (responseBody.get("access_token") == null) {
			log.error("Github登录失败----->{}", responseBody.get("error_description"));
			throw new AuthException(responseBody.get("error_description"));
		}

		GitHubAuthenticationToken authRequest = new GitHubAuthenticationToken(responseBody.get("access_token"), "");
		return getAuthenticationManager().authenticate(authRequest);
	}
}