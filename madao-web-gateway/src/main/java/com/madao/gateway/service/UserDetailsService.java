package com.madao.gateway.service;

import com.madao.api.user.UserServiceRpc;
import com.madao.model.dto.user.UserDto;
import com.madao.utils.JsonData;
import com.madao.utils.LogBack;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 查询Swagger用户
 **/
@Service
public class UserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    private ObjectProvider<UserServiceRpc> userServiceRpc;

    @Override
    public Mono<UserDetails> findByUsername(String account) {
        JsonData<UserDto> userInfo = userServiceRpc.getObject().getUserInfo(account);
        if (!userInfo.isStatus()) {
            LogBack.error(userInfo.getMessage());
            return Mono.error(new UsernameNotFoundException(userInfo.getMessage()));
        }
        UserDto defUser = userInfo.getData();
        if (defUser == null) {
            return Mono.error(new UsernameNotFoundException("User Not Found"));
        }
        UserDetails user = User.withUsername(account)
                .password(defUser.getPassword())
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin")).build();
        return Mono.just(user);
    }
}

