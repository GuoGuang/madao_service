package com.codeif.auth.service;

import com.codeif.api.base.LoginLogServiceRpc;
import com.codeif.db.redis.service.RedisService;
import com.codeif.utils.security.JWTAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 登录，认证
 **/
@Service
public class AuthService {

    @Value("${auth.tokenValiditySeconds}")
    int tokenValiditySeconds;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RedisService redisService;

	@Autowired
	LoginLogServiceRpc loginLogServiceRpc;

    @Autowired
    RestTemplate restTemplate;

	public void logout(String token) {
		String key = "user_token:" + JWTAuthentication.getFullAuthorization(token);
		redisService.del(key);
	}
}
