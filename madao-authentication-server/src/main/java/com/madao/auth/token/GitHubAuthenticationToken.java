package com.madao.auth.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class GitHubAuthenticationToken extends UsernamePasswordAuthenticationToken {

	public GitHubAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
		setAuthenticated(false);
	}

	public GitHubAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
		super(principal, "", authorities);
	}


}

