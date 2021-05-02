package com.madao.auth.service;

import com.madao.api.base.LoginLogServiceRpc;
import com.madao.db.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 登录，认证
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
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
