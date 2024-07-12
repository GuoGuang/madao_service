package com.madao.auth.config;

import com.madao.auth.filter.CaptchaAuthenticationFilter;
import com.madao.auth.filter.GithubAuthenticationFilter;
import com.madao.auth.filter.SmsCodeAuthenticationFilter;
import com.madao.auth.handler.CustomAuthenticationFailureHandler;
import com.madao.auth.handler.CustomAuthenticationSuccessHandler;
import com.madao.auth.handler.OauthLoginSuccessHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * WebServerSecurity配置
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Order(-1)
@Slf4j
@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class OauthWebServerSecurityConfig {
    private final AuthenticationManager authenticationManager;
    private final DataSource dataSource;
    private final ValidateCodeSecurityConfig validateCodeSecurityConfig;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final OauthLoginSuccessHandler oauthLoginSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/auth/**", "/connect/**",
                "/v3/api-docs",
                "/swagger-resources/**",
                "/swagger-ui/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                                .requestMatchers("/v3/api-docs", "/swagger-resources/**", "/swagger-ui/**").permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .logout(logout -> logout.invalidateHttpSession(false).logoutUrl("/auth/logout"))
                // jwt登录
                .oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()))
                // github登录
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(System.out::println)
                        .redirectionEndpoint(redirection -> redirection.baseUri("/auth/login/github"))
                        .tokenEndpoint(System.out::println)
                        .successHandler(oauthLoginSuccessHandler)
                );
        http.addFilterAfter(smsCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(captchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(githubAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .with(validateCodeSecurityConfig, Customizer.withDefaults());
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        return http.build();
    }

    /**
     * 短信验证码登录配置
     **/
    @Bean
    public SmsCodeAuthenticationFilter smsCodeAuthenticationFilter() {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(authenticationManager);
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        return smsCodeAuthenticationFilter;
    }

    /**
     * 图片验证码自定义配置
     **/
    @Bean
    public CaptchaAuthenticationFilter captchaAuthenticationFilter() {
        CaptchaAuthenticationFilter captchaAuthenticationFilter = new CaptchaAuthenticationFilter();
        captchaAuthenticationFilter.setAuthenticationManager(authenticationManager);
        captchaAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        captchaAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        return captchaAuthenticationFilter;
    }

    @Bean
    public GithubAuthenticationFilter githubAuthenticationFilter() {
        GithubAuthenticationFilter githubAuthenticationFilter = new GithubAuthenticationFilter();
        githubAuthenticationFilter.setAuthenticationManager(authenticationManager);
        githubAuthenticationFilter.setAuthenticationSuccessHandler(oauthLoginSuccessHandler);
        githubAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        return githubAuthenticationFilter;
    }

    /**
     * 记住我功能的token存取器配置
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
}
