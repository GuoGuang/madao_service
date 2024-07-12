package com.madao.auth.config;

import com.madao.auth.provider.CaptchaAuthenticationProvider;
import com.madao.auth.provider.GithubAuthenticationProvider;
import com.madao.auth.provider.SmsCodeAuthenticationProvider;
import com.madao.constant.CommonConst;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.io.FileInputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

/**
 * Oauth2认证配置
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Configuration
public class OauthAuthorizationServerConfig {
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    UserDetailsService userDetailsService;
    @Autowired
    private SmsCodeAuthenticationProvider smsCodeAuthenticationProvider;
    @Autowired
    private CaptchaAuthenticationProvider captchaAuthenticationProvider;
    @Autowired
    private GithubAuthenticationProvider githubAuthenticationProvider;

    /**
     * 配置客户端应用
     * 如果要实现类似GitHub、Google那种支持开发者申请APP或者有多个不同系统的可以将此处改为从数据库动态取数据加载。
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient loginClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("XcWebApp")
                .clientSecret("XcWebApp")
                //授权客户端请求认证服务的类型authorization_code：根据授权码生成令牌，
                // client_credentials:客户端认证，refresh_token：刷新令牌，password：密码方式认证
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/login-client")
                .redirectUri("http://127.0.0.1:8080/authorized")
                //客户端范围，名称自定义，必填
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope("app")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .tokenSettings(TokenSettings.builder()
                        //访问令牌有效期
                        .accessTokenTimeToLive(Duration.ofDays(CommonConst.TIME_OUT_DAY))
                        //刷新令牌有效期
                        .refreshTokenTimeToLive(Duration.ofDays(CommonConst.TIME_OUT_DAY))
                        .build())
                .build();
        return new InMemoryRegisteredClientRepository(loginClient);
    }

    /**
     * 构建AuthorizationServerConfig.configure(AuthorizationServerEndpointsConfigurer endpoints)
     * 所需的authenticationManager
     * 目前支持验证码和手机验证码登录
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providers = List.of(smsCodeAuthenticationProvider, captchaAuthenticationProvider, githubAuthenticationProvider);
        return new ProviderManager(providers);
    }


    @Bean("keyProp")
    public KeyProperties keyProperties() {
        return new KeyProperties();
    }

    /**
     * 从指定的keystore文件中加载并返回KeyPair。
     *
     * @return 从keystore中提取的KeyPair对象，其中包含公钥和私钥。
     */
    @Bean
    public KeyPair keyPair() {
        KeyPair keyPair;
        try {
            KeyStore keystore = KeyStore.getInstance("JKS");
            try (FileInputStream fis = new FileInputStream(keyProperties().getKeyStore().getLocation().getFile())) {
                keystore.load(fis, keyProperties().getKeyStore().getSecret().toCharArray());
            }
            PrivateKey privateKey = (PrivateKey) keystore.getKey(keyProperties().getKeyStore().getAlias(), keyProperties().getKeyStore().getPassword().toCharArray());
            Certificate cert = keystore.getCertificate(keyProperties().getKeyStore().getAlias());
            keyPair = new KeyPair(cert.getPublicKey(), privateKey);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(KeyPair keyPair) {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(KeyPair keyPair) {
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(KeyPair keyPair) {
        return new NimbusJwtEncoder(jwkSource(keyPair));
    }

}

