package com.madao.utils.security;

import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

@NoArgsConstructor
public final class SecurityUtils {

	public static final String ADMIN = "ROLE_ADMIN";
	public static final String USER = "ROLE_USER";
	public static final String ANONYMOUS = "ROLE_ANONYMOUS";

	public static String getCurrentUserId() {
		 return getTokenAttributes().getOrDefault("id","").toString();
	}

	/**
	 * 提取授权信息中的主要信息。
	 */
	public static Map<String, Object> getTokenAttributes() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return ((JwtAuthenticationToken) securityContext.getAuthentication()).getTokenAttributes();
	}

	/**
	 * 检查用户是否经过身份验证。
	 *
	 * @return 如果用户已通过身份验证，则为 true，否则为 false。
	 */
	public static boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && getAuthorities(authentication).noneMatch(ANONYMOUS::equals);
	}

	/**
	 * 检查当前用户是否具有任何权限
	 */
	public static boolean hasCurrentUserAnyOfAuthorities(String... authorities) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (
				authentication != null && getAuthorities(authentication).anyMatch(authority -> Arrays.asList(authorities).contains(authority))
		);
	}

	/**
	 * 检查当前用户是否没有任何权限。
	 */
	public static boolean hasCurrentUserNoneOfAuthorities(String... authorities) {
		return !hasCurrentUserAnyOfAuthorities(authorities);
	}

	/**
	 * 检查当前用户是否具有特定权限。
	 */
	public static boolean hasCurrentUserThisAuthority(String authority) {
		return hasCurrentUserAnyOfAuthorities(authority);
	}

	private static Stream<String> getAuthorities(Authentication authentication) {
		return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
	}
}
