package com.madao.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 查询Swagger用户
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
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

