package com.ibole.auth.service;

import com.ibole.api.base.LoginLogServiceRpc;
import com.ibole.cache.redis.RedisService;
import com.ibole.utils.security.JWTAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 登录，认证
 * @author : LGG
 * @create : 2019-06-18 14:34
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
