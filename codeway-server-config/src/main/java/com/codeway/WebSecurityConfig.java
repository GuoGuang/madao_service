package com.codeway;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * actuator、key禁止访问
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/actuator/**").authenticated()
                .antMatchers("/key/**").authenticated()
                .anyRequest().permitAll();
    }
  /*  @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/actuator/**");
    }*/
}