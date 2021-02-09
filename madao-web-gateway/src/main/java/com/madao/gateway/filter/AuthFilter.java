package com.madao.gateway.filter;

import com.madao.db.redis.service.RedisService;
import com.madao.enums.StatusEnum;
import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.utils.JsonData;
import com.madao.utils.JsonUtil;
import com.madao.utils.LogBack;
import javassist.NotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

/**
 *
 * {
 *     "skipAuth":true,
 *     "publicKey":"xxx"
 * }
 * @deprecated  {@link TokenFilter} 一般项目不需要全局接口加密，如果需要再放开
 * @author LGG
 */
@Component
@Deprecated
public class AuthFilter implements GatewayFilter, Ordered {

	private final RedisService redisService;
	public AuthFilter(RedisService redisService) {
		this.redisService = redisService;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		ServerHttpResponse response = exchange.getResponse();
		MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
		response.setStatusCode(HttpStatus.OK);
		response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		//后端调用跳过验签
		boolean skipAuth = Boolean.parseBoolean(queryParams.getFirst("skipAuth"));
		if (skipAuth){
			chain.filter(exchange);
		}

		String sign = queryParams.getFirst("signature");
		if (StringUtils.isEmpty(sign)) {
			return exchange.getResponse().writeWith(Flux.just(
					this.getBodyBuffer(exchange.getResponse(),  JsonData.failed(StatusEnum.NO_SIGNATURE_PARAMETER))));
		}
		String publicKey = exchange.getRequest().getHeaders().getFirst("publicKey");
		if (StringUtils.isEmpty(publicKey)) {
			return exchange.getResponse().writeWith(Flux.just(
					this.getBodyBuffer(exchange.getResponse(), JsonData.failed(StatusEnum.NO_PUBLIC_KEY_PARAMETER))));
		}
		String privateKey = (String) redisService.get(publicKey).orElse(null);
		if (StringUtils.isEmpty(privateKey)){
			return exchange.getResponse().writeWith(Flux.just(
					this.getBodyBuffer(exchange.getResponse(), JsonData.failed(StatusEnum.PRIVATE_KEY_EXPIRED))));
		}

		TreeMap<String, List<String>> parameterMap = new TreeMap<>(exchange.getRequest().getQueryParams());
		//验签
		StringBuilder sb = new StringBuilder();
		parameterMap.forEach((key, value) -> {
			if (!StringUtils.equalsIgnoreCase(key, "signature")) {
				if (!CollectionUtils.isEmpty(value)) {
					sb.append(key);
					sb.append("=");
					sb.append(value.stream().findFirst().get());
				}
			}
		});
		sb.append("privateKey=");
		sb.append(privateKey);
		System.out.println(sb.toString());
		String serverSign = DigestUtils.md5Hex(sb.toString());
		System.out.println(serverSign);
		if (!serverSign.equals(sign)) {
			return exchange.getResponse().writeWith(Flux.just(
					this.getBodyBuffer(exchange.getResponse(), JsonData.failed(StatusEnum.VERIFICATION_NOT_PASS))));
		}
		return chain.filter(exchange);
	}

	/**
	 * 封装返回值
	 */
	private DataBuffer getBodyBuffer(ServerHttpResponse response, JsonData<Object> result) {
		try {
			return response.bufferFactory().wrap(JsonUtil.toJSONBytes(result));
		} catch (IOException e) {
			LogBack.error(e.getMessage(), e);
			throw new RuntimeException("封装返回值异常！e:{}",e);
		}
	}

	@Override
	public int getOrder() {
		return -200;
	}}