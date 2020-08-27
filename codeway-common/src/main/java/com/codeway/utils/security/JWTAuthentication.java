package com.codeway.utils.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.codeway.model.pojo.user.User;
import com.codeway.utils.DateUtil;
import com.codeway.utils.JsonUtil;
import com.codeway.utils.LogBack;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JWT工具类
 **/
//@ConfigurationProperties("jwt.config")
public class JWTAuthentication {

	// 签名秘钥
	private static final String  SECRET = "session_secret";
	// jwt 签发者
	private static final String  ISSUER = "excloud";

	/**
	 * Authorization认证开头是"bearer "
	 */
	private static final int BEARER_BEGIN_INDEX = 7;
	//公钥
	private static final String PUBLIC_KEY = "publickey.txt";

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
		DecodedJWT jwt = JWT.decode(jwtStr);
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

	public static boolean invalidJwtAccessToken(String authentication) {
		//verifier = Optional.ofNullable(verifier).orElse(new MacSigner(signingKey));
		//是否无效true表示无效
		boolean invalid = Boolean.TRUE;
		try {
			String pubKey = JWTAuthentication.getPubKey(PUBLIC_KEY);
			RsaVerifier rsaVerifier = new RsaVerifier(pubKey);
			Jwt jwt = JwtHelper.decode(authentication);
			jwt.verifySignature(rsaVerifier);
			invalid = Boolean.FALSE;
		} catch (InvalidSignatureException | IllegalArgumentException ex) {
			LogBack.error("user token has expired or signature error", ex);
		}
		return invalid;
	}
	/**
	 * 获取非对称加密公钥 Key
	 * publicKey：公钥
	 * @return 公钥 Key
	 */
	public static String getPubKey(String publicKey) {
		Resource resource = new ClassPathResource(publicKey);
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
			BufferedReader br = new BufferedReader(inputStreamReader);
			return br.lines().collect(Collectors.joining("\n"));
		} catch (IOException ioe) {
			LogBack.error("getPubKey 异常：", ioe);
			return null;
		}
	}

	/**
	 * 拼接 Bearer 令牌
	 * @param auth 令牌
	 */
	public static String getFullAuthorization(String auth) {
		return StringUtils.substring(auth, BEARER_BEGIN_INDEX);
	}
}
