package com.ibole.gateway.handle;/*
package com.ibole.authorization.handler;

import com.ibole.gateway.pojo.security.CodeWebAuthenticationDetails;
import com.ibole.gateway.service.WebReactiveUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthenticationProvider;
import org.springframework.security.authorization.BadCredentialsException;
import org.springframework.security.authorization.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


*/
/**
 * 自定义认证
 * @see https://blog.csdn.net/wangb_java/article/details/86636352
 **//*



@Component
public class PasswordAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	WebReactiveUserDetailsService userDetailsService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authorization) throws AuthenticationException {

		// 如有验证码，使用自定义WebAuthenticationDetails
		CodeWebAuthenticationDetails details = (CodeWebAuthenticationDetails)authorization.getDetails();
		if(!details.getCode().equals("123")){
			throw new BadCredentialsException("验证码错误");
		}


		String userName = (String) authorization.getPrincipal(); // 这个获取表单输入中返回的用户名;
		String password = (String) authorization.getCredentials(); // 这个是表单中输入的密码；

		String encodePassword = passwordEncoder.encode(password);
		UserDetails userInfo = userDetailsService.loadUserByUsername(userName);

		if (!userInfo.getPassword().equals(encodePassword)) {
			throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
		}

		// 认证通过，从数据库中查询用户权限
		*/
/*List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_role1"));*//*


		return new UsernamePasswordAuthenticationToken(userName, password, userInfo.getAuthorities());
	}

	*/
/**
	 * supports方法用于检查入参的类型，AuthenticationProvider只会认证符合条件的类型
	 * 检查入参Authentication是否是UsernamePasswordAuthenticationToken或它的子类
	 *
	 * 系统默认的Authentication入参都是UsernamePasswordAuthenticationToken类型，所以这里supports必须为true。
	 * 需自定义认证过滤器，到时候就可以自定义不同的入参类型了，以适用于不同的AuthenticationProvider。

	 * @param authorization 符合条件的类型
	 * @return boolean
	 *//*

	@Override
	public boolean supports(Class<?> authorization) {
		return true;
	}
}
*/
