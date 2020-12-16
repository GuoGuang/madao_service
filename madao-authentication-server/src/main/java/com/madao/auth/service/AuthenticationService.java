package com.madao.auth.service;

import com.madao.api.base.LoginLogServiceRpc;
import com.madao.db.redis.service.RedisService;
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
