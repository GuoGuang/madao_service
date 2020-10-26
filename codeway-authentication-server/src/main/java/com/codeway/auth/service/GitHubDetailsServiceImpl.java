package com.codeway.auth.service;

import com.codeway.api.user.BlogUserServiceRpc;
import com.codeway.exception.custom.RemoteRpcException;
import com.codeway.model.dto.user.RoleDto;
import com.codeway.model.dto.user.UserDto;
import com.codeway.utils.JsonData;
import com.codeway.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用户信息服务
 * 实现 Spring Security的UserDetailsService接口方法，用于身份认证
 **/
@Service
public class GitHubDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private BlogUserServiceRpc blogUserServiceRpc;

	/**
	 * 根据github node_id查询用户信息，存在则返回登录，否则新建并返回登录
	 *
	 * @param userInfo githubUserInfo
	 * @return 用于身份认证的 UserDetails 用户信息实体
	 */
	@Override
	public UserDetails loadUserByUsername(String userInfo) {

		JsonData<UserDto> userByUser = blogUserServiceRpc.loginWithGithub(JsonUtil.jsonToMap(userInfo));
		if (!userByUser.isStatus()) {
			throw new RemoteRpcException(userByUser);
		}
		UserDto defUser = userByUser.getData();

		String password = defUser.getPassword();
		List<GrantedAuthority> authorities = new ArrayList<>();
		Set<RoleDto> roles = defUser.getRoles();
		roles.forEach(role ->
				authorities.add(new SimpleGrantedAuthority(role.getId())));
		return new UserJwt(defUser.getUserName(),
				password,
				defUser.getId(),
				defUser.getNickName(),
				defUser.getAvatar(),
				defUser.getEmail(),
				defUser.getPhone(),
				defUser.getAccount(),
				authorities);
	}
}
