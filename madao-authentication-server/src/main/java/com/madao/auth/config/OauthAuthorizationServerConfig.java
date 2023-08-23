package com.madao.auth.config;

import com.madao.auth.handler.CustomTokenEnhancer;
import com.madao.auth.handler.CustomWebResponseExceptionTranslator;
import com.madao.auth.provider.CaptchaAuthenticationProvider;
import com.madao.auth.provider.GithubAuthenticationProvider;
import com.madao.auth.provider.SmsCodeAuthenticationProvider;
import com.madao.constant.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.Arrays;
import java.util.List;

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
public class OauthAuthorizationServerConfig  {

    @Autowired
    CustomUserAuthenticationConverter customUserAuthenticationConverter;

    //读取密钥的配置
    @Bean("keyProp")
    public KeyProperties keyProperties() {
        return new KeyProperties();
    }


    @Autowired
    @Qualifier("userDetailsServiceImpl")
    UserDetailsService userDetailsService;

    // jwt令牌转换器
    // 登录错误时执行
    @Autowired
    private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;

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
    public ClientDetailsService clientDetailsService() throws Exception {
       ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder> clients = new InMemoryClientDetailsServiceBuilder();
        return clients.inMemory()
                .withClient("XcWebApp")//客户端id
                .secret("XcWebApp")//密码，要保密
                .accessTokenValiditySeconds(CommonConst.TIME_OUT_DAY)//访问令牌有效期
                .refreshTokenValiditySeconds(CommonConst.TIME_OUT_DAY)//刷新令牌有效期
                //授权客户端请求认证服务的类型authorization_code：根据授权码生成令牌，
                // client_credentials:客户端认证，refresh_token：刷新令牌，password：密码方式认证
                .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token", "password")
                .scopes("app")//客户端范围，名称自定义，必填
                .and().build();
    }


    /**
     * 构建AuthorizationServerConfig.configure(AuthorizationServerEndpointsConfigurer endpoints)
     * 所需的authenticationManager
     * 目前支持验证码和手机验证码登录
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providers =  List.of(smsCodeAuthenticationProvider,captchaAuthenticationProvider,githubAuthenticationProvider);
        return new ProviderManager(providers);
    }

    /**
     * 配置AccessToken加密方式
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(keyProperties().getKeyStore().getLocation(),
                keyProperties().getKeyStore().getSecret().toCharArray())
                .getKeyPair(keyProperties().getKeyStore().getAlias(),
                        keyProperties().getKeyStore().getPassword().toCharArray());
        converter.setKeyPair(keyPair);
        //配置自定义的CustomUserAuthenticationConverter
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);
        return converter;
    }


    /**
     * 自定义token
     */
    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomTokenEnhancer(), jwtAccessTokenConverter()));
        return tokenEnhancerChain;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 端点配置
     * 配置用户认证
     * 所有的用户认证逻辑，由authenticationManager统一管理
     */
    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices() throws Exception {
        AuthorizationServerEndpointsConfigurer endpoints = new AuthorizationServerEndpointsConfigurer();
        endpoints.setClientDetailsService(clientDetailsService());
        return endpoints.accessTokenConverter(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager())//认证管理器
                .tokenStore(tokenStore())//令牌存储
                .tokenEnhancer(tokenEnhancerChain())
                .exceptionTranslator(customWebResponseExceptionTranslator)
                .userDetailsService(userDetailsService).getTokenServices();//用户信息service
    }
}

