package com.youyd.gateway.filter;

import com.youyd.cache.constant.RedisConstant;
import com.youyd.cache.redis.RedisService;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.ErrotResult;
import com.youyd.pojo.user.User;
import com.youyd.utils.security.JWTAuthentication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 *  GateWay全局过滤器
 * @author : LGG
 * @create : 2018-12-28
 * @see http://www.manongjc.com/article/43669.html
 * @see https://windmt.com/2018/05/08/spring-cloud-14-spring-cloud-gateway-filter/
 **/

@Component
public class TokenFilter implements GlobalFilter, Ordered {


	@Autowired
	private RedisService redisService;

	@Value("${auth.skip.urls}")
	private String[] skipAuthUrls;

	/**
	 * 获取token字段，如果能获取到就 pass，获取不到就直接返回401错误，
	 * chain.filter(exchange)之前的就是 “pre” 部分，之后的也就是then里边的是 “post” 部分
	 * @param exchange
	 * @param chain
	 * @return
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String path = exchange.getRequest().getURI().getPath();
		//跳过不需要验证的路径
		if(Arrays.asList(skipAuthUrls).contains(path)){
			return chain.filter(exchange);
		}

		String token = exchange.getRequest().getHeaders().getFirst("x-token");
		if (StringUtils.isEmpty(token)) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}else if (verifyToken(token)){
			ServerHttpResponse response = exchange.getResponse();
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			ErrotResult errotResult = new ErrotResult(StatusEnum.LOGIN_EXPIRED);
			byte[] result = errotResult.toString().getBytes(StandardCharsets.UTF_8);
			DataBuffer buffer = response.bufferFactory().wrap(result);
			return response.writeWith(Flux.just(buffer));
		}
		return chain.filter(exchange);
	}

	private boolean verifyToken(String token) {
		User user = JWTAuthentication.parseJwtToSubject(token);
		User userToken = (User)redisService.get(RedisConstant.REDIS_KEY_TOKEN + user.getId());
		return userToken == null;
	}

	@Override
	public int getOrder() {
		return 100;
	}

}
