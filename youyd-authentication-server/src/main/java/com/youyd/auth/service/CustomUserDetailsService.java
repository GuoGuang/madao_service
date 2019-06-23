package com.youyd.auth.service;

import com.youyd.api.user.UserServiceRpc;
import com.youyd.pojo.user.User;
import com.youyd.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息服务
 * 实现 Spring Security的UserDetailsService接口方法，用于身份认证
 * @author : LGG
 * @create : 2019-06-18 14:34
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
		//远程调用用户中心根据账号查询用户信息
		com.youyd.pojo.user.User defUser = userService.findUserByUser(user).getData();
		if(defUser == null){
			return null;
		}
		String password = defUser.getPassword();
		//List<Menu> userMenus = defUser.getMenus();
		List<String> menus = new ArrayList<>();
		//userMenus.forEach(item-> menus.add(item.getId()));
		String user_permission_string  = StringUtils.join(menus.toArray(), ",");
		UserJwt userDetails = new UserJwt(defUser.getUserName(),
				password,
				defUser.getId(),
				defUser.getNickName(),
				defUser.getAvatar(),
				defUser.getEmail(),
				defUser.getPhone(),
				defUser.getAccount(),
				AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));

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
