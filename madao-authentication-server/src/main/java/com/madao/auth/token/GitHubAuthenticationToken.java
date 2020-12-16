package com.madao.auth.token;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class GitHubAuthenticationToken extends UsernamePasswordAuthenticationToken {

	public GitHubAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
		setAuthenticated(false);
	}

	public GitHubAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
		super(principal, "", authorities);
	}


}

