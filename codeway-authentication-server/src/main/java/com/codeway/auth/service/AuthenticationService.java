package com.madaoo.auth.service;

import com.madaoo.api.base.LoginLogServiceRpc;
import com.madaoo.db.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 登录，认证
 **/
@Service
public class AuthenticationService {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RedisService redisService;

	@Autowired
	LoginLogServiceRpc loginLogServiceRpc;

    @Autowired
    RestTemplate restTemplate;

}
