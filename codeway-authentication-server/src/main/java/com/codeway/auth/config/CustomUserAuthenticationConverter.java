package com.codeway.auth.config;

import com.codeway.auth.service.UserJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 自定义access_token内容
 **/
@Component
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

	private final UserDetailsService userDetailsServiceImpl;

	public CustomUserAuthenticationConverter(UserDetailsService userDetailsServiceImpl) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

	/**
	 * 定义access_token内容，JWT谁都可读
	 * 不应该在载荷里面加入任何敏感的数据
	 */
	@Override
	public Map<String, ?> convertUserAuthentication(Authentication authentication) {
		LinkedHashMap<String,Object> response = new LinkedHashMap<>();
		String name = authentication.getName();
		Object principal = authentication.getPrincipal();
		UserJwt userJwt = null;
		if(principal instanceof  UserJwt){
			userJwt = (UserJwt) principal;
		}else{
			//refresh_token默认不去调用userdetailService获取用户信息，手动去调用，得到 UserJwt
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(name);
			userJwt = (UserJwt) userDetails;
		}
		if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
			response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
		}
		response.put("id", userJwt.getId());
		return response;
	}


}

