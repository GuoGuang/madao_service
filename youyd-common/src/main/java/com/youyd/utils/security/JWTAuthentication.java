package com.youyd.utils.security;

import com.youyd.utils.DateUtil;
import io.jsonwebtoken.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @description: S
 * @author: LGG
 * @create: 2018-10-24 19:54
 **/
@Component
@ConfigurationProperties("jwt.config")
public class JWTAuthentication {

	//签名秘钥
	private String encodedSecretKey;
	// 过期时间
	//private long expiration ;


	/**
	 * 生成JWT
	 * @param id 用户id
	 * @param subject 载体，用户数据
	 * @param roles 所属角色组
	 * @param afterDate 过期时间
	 * @return String
	 */
	public String createJWT(Long id, String subject, String roles, LocalDateTime afterDate) {
		long nowMillis = System.currentTimeMillis();
		JwtBuilder builder = Jwts.builder();
		builder.setId(id.toString());
		builder.setSubject(subject);
		// 设置签发时间
		builder.setIssuedAt(DateUtil.convertLdtToDate(DateUtil.getCurrentTime()));
		// 设置签名秘钥
		builder.signWith(SignatureAlgorithm.HS256, encodedSecretKey);
		// 过期时间
		builder.setExpiration(DateUtil.convertLdtToDate(afterDate));
		builder.claim("roles",roles);
		return builder.compact();
	}

	/**
	 * 解析JWT
	 * @param jwtStr:待解密的jwt
	 * @return
	 */
	public Claims parseJWT(String jwtStr){
		JwtParser parser = Jwts.parser();
		parser.setSigningKey(encodedSecretKey);
		Claims body = parser.parseClaimsJws(jwtStr).getBody();
		return body;
	}




	public String getEncodedSecretKey() {
		return encodedSecretKey;
	}
	public void setEncodedSecretKey(String encodedSecretKey) {
		this.encodedSecretKey = encodedSecretKey;
	}


}
