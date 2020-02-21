package com.codeif.auth.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.codeif.auth.handler.CustomTokenEnhancer;
import com.codeif.auth.handler.CustomWebResponseExceptionTranslator;
import com.codeif.constant.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.util.Arrays;

/**
 * Oauth2认证配置
 **/
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DruidDataSource dataSource;
    //jwt令牌转换器
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    // 登录错误时执行
    @Autowired
    private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenStore tokenStore;

    //读取密钥的配置
    @Bean("keyProp")
    public KeyProperties keyProperties(){
        return new KeyProperties();
    }

    @Resource(name = "keyProp")
    private KeyProperties keyProperties;


    //客户端配置
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

	/**
	 * 指定token存储在数据库还是内存
	 * @param clients
	 * @throws Exception
	 */
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.jdbc(this.dataSource).clients(this.clientDetails());
        clients.inMemory()
                .withClient("XcWebApp")//客户端id
                .secret("XcWebApp")//密码，要保密
                .accessTokenValiditySeconds(CommonConst.TIME_OUT_DAY)//访问令牌有效期
                .refreshTokenValiditySeconds(CommonConst.TIME_OUT_DAY)//刷新令牌有效期
                //授权客户端请求认证服务的类型authorization_code：根据授权码生成令牌，
                // client_credentials:客户端认证，refresh_token：刷新令牌，password：密码方式认证
                .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token", "password")
                .scopes("app");//客户端范围，名称自定义，必填
    }

    //token的存储方法
//    @Bean
//    public InMemoryTokenStore tokenStore() {
//        //将令牌存储到内存
//        return new InMemoryTokenStore();
//    }
//    @Bean
//    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory){
//        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
//        return redisTokenStore;
//    }
	/**
	 * 自定义token
	 * @return tokenEnhancerChain
	 */
	@Bean
	public TokenEnhancerChain tokenEnhancerChain() {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomTokenEnhancer(),jwtAccessTokenConverter));
		return tokenEnhancerChain;
	}

    @Bean
    @Autowired
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

	/**
	 * 配置AccessToken加密方式
	 */
	@Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(CustomUserAuthenticationConverter customUserAuthenticationConverter) {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(
        		        keyProperties.getKeyStore().getLocation(),
		                keyProperties.getKeyStore().getSecret().toCharArray()).getKeyPair(
		                		keyProperties.getKeyStore().getAlias(),
		                        keyProperties.getKeyStore().getPassword().toCharArray());
        converter.setKeyPair(keyPair);
        //配置自定义的CustomUserAuthenticationConverter
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);
        return converter;
    }

	/**
	 * 授权服务器端点配置
	 */
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.accessTokenConverter(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager)//认证管理器
                .tokenStore(tokenStore)//令牌存储
		        .tokenEnhancer(tokenEnhancerChain())
		        .exceptionTranslator(customWebResponseExceptionTranslator)
                .userDetailsService(userDetailsService);//用户信息service
    }


	/**
	 * 授权服务器的安全配置
	 * https://codeday.me/bug/20181222/463600.html
	 * @param oauthServer
	 */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.allowFormAuthenticationForClients()
                .passwordEncoder(new BCryptPasswordEncoder())
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }



}

