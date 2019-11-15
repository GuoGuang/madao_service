/**
 * 
 */
package com.ibole.auth.provider;

import com.ibole.auth.exception.ValidateCodeException;
import com.ibole.auth.service.CustomUserDetailsService;
import com.ibole.auth.token.CaptchaAuthenticationToken;
import com.ibole.cache.redis.RedisService;
import com.ibole.pojo.user.User;
import com.ibole.utils.JsonUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 图片验证码登录验证
 * @author : LGG
 * @create : 2019-06-18 14:34
 **/
@Data
@Component
public class CaptchaAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private RedisService redisService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserDetailsService userDetailsService;


	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		CaptchaAuthenticationToken authenticationToken = (CaptchaAuthenticationToken) authentication;
		String username = authenticationToken.getPrincipal().toString();
		User user1 = new User();
		user1.setAccount(username);
		UserDetails user = userDetailsService.loadUserByUsername(JsonUtil.toJsonString(user1));
		if (user == null){
			throw new ValidateCodeException("用户名或密码错误！");
		}
		String password = user.getPassword();
		String credentials = authentication.getCredentials().toString();
		if (!passwordEncoder.matches(credentials,password)){
			throw new ValidateCodeException("用户名或密码错误！");
		}
		//查询该code拥有的权限
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		// 认证通过，生成已认证的Authentication，加入请求权限
		CaptchaAuthenticationToken authenticationResult = new CaptchaAuthenticationToken(user, user.getAuthorities());
		authenticationResult.setDetails(authenticationToken.getDetails());
		return new CaptchaAuthenticationToken(user,authorities);
	}


	/**
	 * supports方法用于检查入参的类型，AuthenticationProvider只会认证符合条件的类型
	 * 检查入参Authentication是否是UsernamePasswordAuthenticationToken或它的子类
	 *
	 * 系统默认的Authentication入参都是UsernamePasswordAuthenticationToken类型，所以这里supports必须为true。
	 * 需自定义认证过滤器，到时候就可以自定义不同的入参类型了，以适用于不同的AuthenticationProvider。

	 * @param authorization 符合条件的类型
	 * @return boolean
	 */
	public boolean supports(Class<?> authentication) {
		//负责处理MyAuthentication类型登录认证，参考上一篇
		return (CaptchaAuthenticationToken.class.isAssignableFrom(authentication));
	}

}