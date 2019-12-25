package com.ibole.auth.service;

import com.ibole.api.user.UserServiceRpc;
import com.ibole.config.CustomQueryResults;
import com.ibole.pojo.user.Role;
import com.ibole.pojo.user.User;
import com.ibole.utils.JsonData;
import com.ibole.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息服务
 * 实现 Spring Security的UserDetailsService接口方法，用于身份认证
 **/
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserServiceRpc userService;

	@Autowired
	ClientDetailsService clientDetailsService;

	/**
	 * 根据用户名查找账户信息并返回用户信息实体
	 * @param user 用户名
	 * @return 用于身份认证的 UserDetails 用户信息实体
	 */
	@Override
    public UserDetails loadUserByUsername(String userJson) {

		User user = JsonUtil.jsonToPojo(userJson, User.class);
//		Page<User> defUser = userService.findUserByUser(user).getData();
		JsonData<CustomQueryResults<User>> userByUser = userService.findUserByUser(user);
		if (!userByUser.isStatus()) {
			return null;
		}
		List<User> records = userByUser.getData().getResults();
		if (records.size() != 1) {
			return null;
		}
		User defUser = records.get(0);
		String password = defUser.getPassword();
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<Role> roles = records.get(0).getRoles();
		roles.forEach(role ->
				authorities.add(new SimpleGrantedAuthority(role.getId())));
		UserJwt userDetails = new UserJwt(defUser.getUserName(),
				password,
				defUser.getId(),
				defUser.getNickName(),
				defUser.getAvatar(),
				defUser.getEmail(),
				defUser.getPhone(),
				defUser.getAccount(),
				authorities);

	/*    UserDetails userDetails = User.withUsername(defUser.getAccount())
			    .password(defUser.getPassword())
			    .roles("")
			    .build();*/
		return userDetails;
/*
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                user.getEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                this.obtainGrantedAuthorities(user));*/
    }

    /**
     * 获得登录者所有角色的权限集合.
     *
     * @param user
     * @return
     */
    /*private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
        Set<Role> roles = roleService.queryUserRolesByUserId(user.getId());
        log.info("user:{},roles:{}", user.getUsername(), roles);
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode()))
                .collect(Collectors.toSet());
    }*/
}
