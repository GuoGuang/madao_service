package com.madao.user.config;

import com.madao.enums.AuthorityEnum;
import com.madao.user.handler.CustomAccessDeniedHandler;
import com.madao.user.handler.CustomAuthenticationEntryPoint;
import com.madao.utils.security.JWTAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityFilterChainConfig {

    public static final String PARAM_NAME_ON_AUTHORITY = AuthorityEnum.ROLE_ADMIN.getParamNameOnAuthority();

    /**
     * 公钥
     */
    private static final String PUBLIC_KEY = "publickey.txt";

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("management/health").permitAll()
                        .requestMatchers("/management/**").hasAuthority(PARAM_NAME_ON_AUTHORITY)
                        .requestMatchers(HttpMethod.DELETE).hasAuthority(PARAM_NAME_ON_AUTHORITY)
                        .requestMatchers(HttpMethod.PUT).hasAuthority(PARAM_NAME_ON_AUTHORITY)
                        .requestMatchers("/v2/api-docs",
                                "/swagger-resources/**",
                                "/swagger-ui/**").permitAll()
                        .requestMatchers("/**").authenticated());
        return http.httpBasic()
                .and()
                        .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)).build();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(JWTAuthentication.getPubKey(PUBLIC_KEY));
        return converter;
    }

}
