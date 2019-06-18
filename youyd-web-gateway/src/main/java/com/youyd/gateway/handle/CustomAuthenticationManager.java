package com.youyd.gateway.handle;/*
package com.youyd.gateway.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthenticationManager;
import org.springframework.security.authorization.AuthenticationProvider;
import org.springframework.security.authorization.ProviderManager;
import org.springframework.security.authorization.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * 自定义身份验证管理器：
 * AuthenticationManager
 * 一个AuthenticationProvider对应一种认证逻辑，而有时用户即可选择密码登录，也可选择验证码登录，
 *  这就需要有多个认证逻辑同时存在，即多个AuthenticationProvider 。
 *  而AuthenticationManager就是用来管理多个AuthenticationProvider的。
 *
 *  注意：
 *  如果authenticate方法中存在多重认证流程，即多个自定义登录认证，那么认证流程则是如下：
 *      1. 执行第1个authenticationProvider的supports方法，
 *         结果false会跳到下个authenticationProvider。结果true则执行当前authenticationProvider的认证逻辑。
 *      2.认证不通过，会抛出相关异常，会直接跳出AuthenticationManager。
 *      3.认证通过，返回已认证Authentication，同样跳出AuthenticationManager。
 *      4.如果需要进一步认证，可返回null，跳到下一个authenticationProvider，可实现多重认证。
 *
 * @author : LGG
 * @create : 2019/6/10 2:03:05
 **//*


@Component
public class CustomAuthenticationManager implements AuthenticationManager {


	// 自定义安全认证：账号密码登录
	@Autowired
	PasswordAuthenticationProvider passwordAuthenticationProvider;

	*/
/**
	 * 加载自定义的AuthenticationProvider
	 * @param authorization
	 * @return Authentication
	 *//*

	@Override
	public Mono<Authentication> authenticate(Authentication authorization) {
		List<AuthenticationProvider> list = new ArrayList();
		list.add(passwordAuthenticationProvider);
		return new ProviderManager(list);
	}
}
*/
