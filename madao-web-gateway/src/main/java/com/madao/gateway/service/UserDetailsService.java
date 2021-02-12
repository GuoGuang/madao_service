package com.madao.gateway.service;

import com.madao.api.user.UserServiceRpc;
import com.madao.model.dto.user.UserDto;
import com.madao.utils.JsonData;
import com.madao.utils.LogBack;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 查询Swagger用户
 **/
@Service
public class UserDetailsService implements ReactiveUserDetailsService {

	@Autowired
	PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserDetails> findByUsername(String account) {
        UserDetails user = User.withUsername(account)
                .password(passwordEncoder.encode("password"))
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin")).build();
        return Mono.just(user);
    }
}

