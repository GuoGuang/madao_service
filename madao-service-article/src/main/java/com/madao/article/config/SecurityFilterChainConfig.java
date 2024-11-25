package com.madao.article.config;

import com.madao.article.handler.CustomAccessDeniedHandler;
import com.madao.article.handler.CustomAuthenticationEntryPoint;
import com.madao.enums.AuthorityEnum;
import com.madao.utils.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 资源服务器的配置
 * 以及
 * 安全相关的配置
 */
@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityFilterChainConfig {

    public static final String PARAM_NAME_ON_AUTHORITY = AuthorityEnum.ROLE_ADMIN.getParamNameOnAuthority();

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .headers(header -> header
                        .contentTypeOptions(Customizer.withDefaults())
                        .xssProtection(Customizer.withDefaults())
                        .cacheControl(Customizer.withDefaults())
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.GET, "/user/info").permitAll()
                        .requestMatchers(
                                "/v3/api-docs",
                                "/api/**",
                                "/swagger-resources/**",
                                "/management/health/**",
                                "/error",
                                "/swagger-ui/**").permitAll()
                        .requestMatchers("/management/**").hasAuthority(PARAM_NAME_ON_AUTHORITY)
                        .requestMatchers(HttpMethod.DELETE).hasAuthority(PARAM_NAME_ON_AUTHORITY)
                        .requestMatchers(HttpMethod.PUT).hasAuthority(PARAM_NAME_ON_AUTHORITY)
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)).build();
    }

    /**
     * 自定义登录之前的JWT校验转换器
     * 1、支持hasAuthority()的逻辑
     * 2、waiting
     */
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix(""); // 移除默认的 "ROLE_" 前缀
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities"); // 指定解析的字段名
        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return authenticationConverter;
    }

    /**
     * token解码方式
     * 采用公私钥的方式
     */
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(TokenProvider.RSA_PUBLIC_KEY).build();
    }
}
