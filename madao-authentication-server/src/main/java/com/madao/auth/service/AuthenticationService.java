package com.madao.auth.service;

import com.madao.api.BaseServiceRpc;
import com.madao.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 登录，认证
 *
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
	RedisUtil redisUtil;

	@Autowired
	BaseServiceRpc baseServiceRpc;

	@Autowired
	RestTemplate restTemplate;

}
