package com.madao.utils.security;

import com.madao.exception.custom.ParamException;
import com.madao.management.SecurityMetersService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;

/**
 * Token相关工具类
 * 集成普罗米修斯，监控token分发情况
 */
@Component
@Slf4j
public class TokenProvider {

    public static RSAPublicKey RSA_PUBLIC_KEY;
    public static final String BEARER = "Bearer";
    // Authorization认证开头是"bearer "
    public static final int BEARER_BEGIN_INDEX = 7;
    private static final String INVALID_JWT_TOKEN = "Invalid JWT token.";
    private final JwtParser jwtParser;
    private final SecurityMetersService securityMetersService;

    public TokenProvider(SecurityMetersService securityMetersService,@Value("${com.madao.secretKey:}")String secretKey ) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (secretKey.isEmpty()) throw new ParamException("请配置公钥！");

        // V1供JwtAuthenticationFilter使用的自定义过滤器配置
        byte[] publicKeyBytes = Base64.getDecoder().decode(secretKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);
        jwtParser = Jwts.parserBuilder().setSigningKey(publicKey).build();

        // V2供.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) SpringSecurity6内置JWT解析器使用
        RSA_PUBLIC_KEY = (RSAPublicKey) publicKey;

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
     * 基于token获取Authentication
     *
     * @param token
     * @return
     */
    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get("authorities").toString().split(","))
                .filter(auth -> !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .toList();
        User principal = new User(claims.get("id",String.class), "", authorities);

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
            log.trace(INVALID_JWT_TOKEN, e);
            this.securityMetersService.trackTokenExpired();
        } catch (UnsupportedJwtException e) {
            this.securityMetersService.trackTokenUnsupported();
        } catch (MalformedJwtException e) {
            log.trace(INVALID_JWT_TOKEN, e);
            this.securityMetersService.trackTokenMalformed();
        } catch (SignatureException e) {
            log.trace(INVALID_JWT_TOKEN, e);
            this.securityMetersService.trackTokenInvalidSignature();
        } catch (IllegalArgumentException e) {
            // TODO: should we let it bubble (no catch), to avoid defensive programming and follow the fail-fast principle?
            log.error("Token validation error {}", e.getMessage());
        }
        return false;
    }

}
