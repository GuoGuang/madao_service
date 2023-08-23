package com.madao.user;

import com.madao.utils.security.SecurityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the {@link SecurityUtils} utility class.
 */
class SecurityUtilsUnitTest {

	public static final String ADMIN = "ROLE_ADMIN";
	public static final String USER = "ROLE_USER";
	public static final String ANONYMOUS = "ROLE_ANONYMOUS";

	@BeforeEach
	@AfterEach
	void cleanup() {
		SecurityContextHolder.clearContext();
	}

	@Test
	void testGetCurrentUserLogin() {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin"));
		SecurityContextHolder.setContext(securityContext);
		assertThat(SecurityUtils.getCurrentUserId()).contains("admin");
	}

	@Test
	void testIsAuthenticated() {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin"));
		SecurityContextHolder.setContext(securityContext);
		boolean isAuthenticated = SecurityUtils.isAuthenticated();
		assertThat(isAuthenticated).isTrue();
	}

	@Test
	void testAnonymousIsNotAuthenticated() {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(ANONYMOUS));
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("anonymous", "anonymous", authorities));
		SecurityContextHolder.setContext(securityContext);
		boolean isAuthenticated = SecurityUtils.isAuthenticated();
		assertThat(isAuthenticated).isFalse();
	}

	@Test
	void testHasCurrentUserThisAuthority() {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(USER));
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("user", "user", authorities));
		SecurityContextHolder.setContext(securityContext);

		assertThat(SecurityUtils.hasCurrentUserThisAuthority(USER)).isTrue();
		assertThat(SecurityUtils.hasCurrentUserThisAuthority(ADMIN)).isFalse();
	}

	@Test
	void testHasCurrentUserAnyOfAuthorities() {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(USER));
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("user", "user", authorities));
		SecurityContextHolder.setContext(securityContext);

		assertThat(SecurityUtils.hasCurrentUserAnyOfAuthorities(USER, ADMIN)).isTrue();
		assertThat(SecurityUtils.hasCurrentUserAnyOfAuthorities(ANONYMOUS, ADMIN)).isFalse();
	}

	@Test
	void testHasCurrentUserNoneOfAuthorities() {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(USER));
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("user", "user", authorities));
		SecurityContextHolder.setContext(securityContext);

		assertThat(SecurityUtils.hasCurrentUserNoneOfAuthorities(USER, ADMIN)).isFalse();
		assertThat(SecurityUtils.hasCurrentUserNoneOfAuthorities(ANONYMOUS, ADMIN)).isTrue();
	}
}
