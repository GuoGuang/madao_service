package com.ibole.gateway.filter;

import com.ibole.db.redis.service.RedisService;
import com.ibole.utils.JsonData;
import com.ibole.utils.JsonUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

@Component
public class AuthFilter implements GatewayFilter, Ordered {
	@Autowired
	private RedisService redisService;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		exchange.getResponse().setStatusCode(HttpStatus.OK);
		exchange.getResponse().getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		JsonData result = new JsonData();
		//后端调用跳过验签
		boolean skipAuth = Boolean.valueOf(exchange.getRequest().getQueryParams().getFirst("skipAuth"));
		if (!skipAuth) {
			String sign = exchange.getRequest().getQueryParams().getFirst("sign");
			if (StringUtils.isEmpty(sign)) {
				//没有验签参数
				result.setCode(3333);
				result.setMessage("没有验签参数");
				return exchange.getResponse().writeWith(Flux.just(this.getBodyBuffer(exchange.getResponse(), result)));
			}
			String publicKey = exchange.getRequest().getHeaders().getFirst("publicKey");
			if (StringUtils.isEmpty(publicKey)) {
				//没有公钥
				result.setCode(2222);
				result.setMessage("没有公钥");
				return exchange.getResponse().writeWith(Flux.just(this.getBodyBuffer(exchange.getResponse(), result)));
			}
			String privateKey = (String) redisService.get(publicKey);
			if (!StringUtils.isEmpty(privateKey)) {
				TreeMap<String, List<String>> parameterMap = new TreeMap<>(exchange.getRequest().getQueryParams());
				//验签
				StringBuilder sb = new StringBuilder();
				parameterMap.entrySet().forEach(stringEntry -> {
					if (!StringUtils.equalsIgnoreCase(stringEntry.getKey(), "sign")) {
						if (!CollectionUtils.isEmpty(stringEntry.getValue())) {
							sb.append(stringEntry.getKey());
							sb.append("=");
							sb.append(stringEntry.getValue().stream().findFirst().get());
						}
					}
				});
				sb.append("privateKey=");
				sb.append(privateKey);
				System.out.println(sb.toString());
				String serverSign = DigestUtils.md5Hex(sb.toString());
				System.out.println(serverSign);
				if (!serverSign.equals(sign)) {
					//验签不通过
					result.setCode(0000);
					result.setMessage("验签不通过");
					return exchange.getResponse().writeWith(Flux.just(this.getBodyBuffer(exchange.getResponse(), result)));
				}
			} else {
				//私钥过期
				result.setCode(1111);
				result.setMessage("私钥过期");
				return exchange.getResponse().writeWith(Flux.just(this.getBodyBuffer(exchange.getResponse(), result)));
			}
		}
		return chain.filter(exchange);
	}

	/**
	 * 封装返回值
	 *
	 * @param response
	 * @param result
	 * @return
	 */
	private DataBuffer getBodyBuffer(ServerHttpResponse response, JsonData result) {
		try {
			return response.bufferFactory().wrap(JsonUtil.toJSONBytes(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getOrder() {
		return -200;
	}}