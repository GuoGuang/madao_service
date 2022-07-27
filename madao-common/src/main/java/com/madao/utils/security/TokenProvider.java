package com.madao.utils.security;

import com.madao.management.SecurityMetersService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Token相关工具类
 * 集成普罗米修斯，监控token分发情况
 */
@Component
@Slf4j
public class TokenProvider {

	public static final String BEARER = "Bearer";
	// Authorization认证开头是"bearer "
	public static final int BEARER_BEGIN_INDEX = 7;
	private static final String AUTHORITIES_KEY = "auth";
	private static final String INVALID_JWT_TOKEN = "Invalid JWT token.";
	//公钥
	private static final String PUBLIC_KEY = "publickey.txt";
	// jwt 签发者
	private static final String ISSUER = "excloud";
	private final Key key;
	private final JwtParser jwtParser;
	private final long tokenValidityInMilliseconds;
	private final long tokenValidityInMillisecondsForRememberMe;
	private final SecurityMetersService securityMetersService;
	private final String secret = "OGZkMDRhYjkxOGRjYTZjODhjZjVkNGZjZWQ5NGFiMTU3NmQ1ZGRhZDQxMzcxYmExNDNhZTkwNDgzNDRhMTFjZWEzYmI2MmM2ZjZjZmJkNDZhMmMzZTEzZjUzYjYyNTNiYmY3NGY3MTI2OGVhOTZlMDViZGJkZGQ5M2RhOGE1Yzk=";

	public TokenProvider(SecurityMetersService securityMetersService) {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		key = Keys.hmacShaKeyFor(keyBytes);
		jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
		this.tokenValidityInMilliseconds = 1000 * 86400;
		this.tokenValidityInMillisecondsForRememberMe = 1000L * 2592000;
		this.securityMetersService = securityMetersService;
	}

	/**
	 * 拼接 Bearer 令牌
	 *
	 * @param auth 令牌
	 */
	public static String getFullAuthorization(String auth) {
		return StringUtils.substring(auth, BEARER_BEGIN_INDEX);
	}

	/**
	 * 创建JWT Token
	 *
	 * @param authentication
	 * @param rememberMe
	 * @return
	 */
	public String createToken(Authentication authentication, boolean rememberMe) {
		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
		long now = (new Date()).getTime();
		Date validity;
		if (rememberMe) {
			validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
		} else {
			validity = new Date(now + this.tokenValidityInMilliseconds);
		}

		return Jwts
				.builder()
				.setSubject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities)
				.signWith(key, SignatureAlgorithm.HS512)
				.setExpiration(validity)
				.compact();
	}

	/**
	 * 基于token获取Authentication
	 *
	 * @param token
	 * @return
	 */
	public Authentication getAuthentication(String token) {
		Claims claims = jwtParser.parseClaimsJws(token).getBody();

		Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				.filter(auth -> !auth.trim().isEmpty())
				.map(SimpleGrantedAuthority::new)
				.toList();
		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

	/**
	 * 验证Token
	 *
	 * @param authToken
	 * @return
	 */
	public boolean validateToken(String authToken) {
		try {
			jwtParser.parseClaimsJws(authToken);
			return true;
		} catch (ExpiredJwtException e) {
			this.securityMetersService.trackTokenExpired();
			log.trace(INVALID_JWT_TOKEN, e);
		} catch (UnsupportedJwtException e) {
			this.securityMetersService.trackTokenUnsupported();
			log.trace(INVALID_JWT_TOKEN, e);
		} catch (MalformedJwtException e) {
			this.securityMetersService.trackTokenMalformed();
			log.trace(INVALID_JWT_TOKEN, e);
		} catch (SignatureException e) {
			this.securityMetersService.trackTokenInvalidSignature();
			log.trace(INVALID_JWT_TOKEN, e);
		} catch (IllegalArgumentException e) {
			// TODO: should we let it bubble (no catch), to avoid defensive programming and follow the fail-fast principle?
			log.error("Token validation error {}", e.getMessage());
		}
		return false;
	}
}
