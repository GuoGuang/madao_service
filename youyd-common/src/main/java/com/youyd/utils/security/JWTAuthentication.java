package com.youyd.utils.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.google.common.collect.Maps;
import com.youyd.pojo.user.User;
import com.youyd.utils.DateUtil;
import com.youyd.utils.JsonUtil;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * JWT工具类
 * @author : LGG
 * @create : 2018-10-24 19:54
 **/
//@ConfigurationProperties("jwt.config")
public class JWTAuthentication {

	// 签名秘钥
	private static final String  SECRET = "session_secret";
	// jwt 签发者
	private static final String  ISSUER = "excloud";


	/**
	 * 生成JWT
	 * @param id 用户id
	 * @param subject 载体，用户数据
	 // * @param claims 所属角色组
	 * @param afterDate 过期时间
	 * @return String
	 */
	public static String createJWT(Long id, String subject,LocalDateTime afterDate) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			JWTCreator.Builder builder = JWT.create()
											.withJWTId(id.toString())
											.withSubject(subject)
											.withIssuer(ISSUER)
											.withIssuedAt(DateUtil.convertLdtToDate(DateUtil.getCurrentTime()))
											.withExpiresAt(DateUtil.convertLdtToDate(afterDate));
			// 传输自定义claims
			//claims.forEach((k,v) -> builder.withClaim(k, v));
			return builder.sign(algorithm);
		} catch (IllegalArgumentException ex) {
			throw new RuntimeException(ex);
		}
	}


	/**
	 * 解析JWT,获取claims
	 * @param jwtStr:待解密的jwt
	 * @return
	 */
	public static Map<String, String> parseJwtToClaims(String jwtStr)  {
		DecodedJWT jwt = parse(jwtStr);
		Map<String, Claim> map = jwt.getClaims();
		Map<String, String> resultMap = Maps.newHashMap();
		map.forEach((k,v) -> resultMap.put(k, v.asString()));
		return resultMap;
	}

	/**
	 * 解析JWT,获取claims
	 * @param jwtStr:待解密的jwt
	 * @return String
	 */
	public static User parseJwtToSubject(String jwtStr)  {
		DecodedJWT jwt = parse(jwtStr);
		String subject = jwt.getSubject();
		return JsonUtil.jsonToPojo(subject,User.class);
	}


	/**
	 * 解析基础信息,返回解码后的JWT
	 * @param jwtStr jwt
	 * @return DecodedJWT
	 */
	private static DecodedJWT parse(String jwtStr) {
		Algorithm algorithm = null;
		try {
			algorithm = Algorithm.HMAC256(SECRET);
		} catch (IllegalArgumentException ex) {
			throw new RuntimeException(ex);
		}
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
		return verifier.verify(jwtStr);
	}

}
