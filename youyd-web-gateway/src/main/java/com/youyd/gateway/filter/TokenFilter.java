package com.youyd.gateway.filter;

import com.youyd.cache.constant.RedisConstant;
import com.youyd.cache.redis.RedisService;
import com.youyd.enums.StatusEnum;
import com.youyd.gateway.service.AuthService;
import com.youyd.pojo.user.AuthToken;
import com.youyd.pojo.user.User;
import com.youyd.utils.JsonData;
import com.youyd.utils.JsonUtil;
import com.youyd.utils.LogBack;
import com.youyd.utils.security.JWTAuthentication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 *  GateWay全局过滤器
 * @author : LGG
 * @create : 2018-12-28
 * @see http://www.manongjc.com/article/43669.html
 * @see https://windmt.com/2018/05/08/spring-cloud-14-spring-cloud-gateway-filter/
 **/

@Component
public class TokenFilter implements GlobalFilter, Ordered {


	private final static String X_CLIENT_TOKEN_USER = "x-client-token-user";
	private final static String X_CLIENT_TOKEN = "x-client-token";
	private static final String BEARER = "Bearer ";


	/**
	 * 由authentication-client模块提供签权的feign客户端
	 */
	@Autowired
	private AuthService authService;

	@Autowired
	private RedisService redisService;

	//@Value("${com.youyd.auth.skip.urls}")
	//private String[] skipAuthUrls;

	/**
	 * 获取token字段，如果能获取到就 pass，获取不到就直接返回401错误，
	 * chain.filter(exchange)之前的就是 “pre” 部分，之后的也就是then里边的是 “post” 部分
	 * @param exchange
	 * @param chain
	 * @return
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		ServerHttpRequest request = exchange.getRequest();
		String authentication = request.getHeaders().getFirst("AUTH");
		String method = request.getMethodValue();
		String url = request.getPath().value();
		LogBack.info("url:{},method:{},headers:{}", url, method, request.getHeaders());
		//不需要网关签权的url
		if (authService.ignoreAuthentication(url)) {
			return chain.filter(exchange);
		}
		// 如果请求未携带token信息, 直接跳出
		if (StringUtils.isBlank(authentication) || !authentication.contains(BEARER)) {
			LogBack.error("url:{},method:{},headers:{}, 请求未携带token信息", url, method, request.getHeaders());
			return unauthorized(exchange,StatusEnum.PARAM_ILLEGAL.getCode(),StatusEnum.PARAM_ILLEGAL.getMsg());
		}

		long expire = authService.getExpire(authentication);
		// 过期
		if(expire<0){
			return unauthorized(exchange,StatusEnum.LOGIN_EXPIRED.getCode(),StatusEnum.LOGIN_EXPIRED.getMsg());
		}
		AuthToken authToken = authService.getAuthToken(authentication);
		String jwtToken = authToken.getJwt_token();
		//调用签权服务看用户是否有权限，若有权限进入下一个filter
		if (authService.hasPermission(jwtToken, url, method)) {
			ServerHttpRequest.Builder builder = request.mutate();
			builder.header(X_CLIENT_TOKEN, "TODO 添加服务间简单认证");//TODO 转发的请求都加上服务间认证token
			//将jwt token中的用户信息传给服务
			builder.header(X_CLIENT_TOKEN_USER, authService.getJwt(jwtToken).getClaims());
			builder.header(HttpHeaders.AUTHORIZATION,BEARER+jwtToken);
			return chain.filter(exchange.mutate().request(builder.build()).build());
		}
		return unauthorized(exchange,StatusEnum.UN_AUTHORIZED.getCode(),StatusEnum.UN_AUTHORIZED.getMsg());
	}

	/**
	 * 连接Redis校验Token有效性，
	 * @param token 前端携带的令牌
	 * @return boolean
	 */
	private boolean verifyToken(String token) {
		User user = JWTAuthentication.parseJwtToSubject(token);
		User userToken = (User)redisService.get(RedisConstant.REDIS_KEY_TOKEN + user.getId());
		return userToken == null;
	}

	@Override
	public int getOrder() {
		return -100;
	}


	/**
	 * 网关拒绝，返回401
	 *
	 * @param
	 */
	private Mono<Void> unauthorized(ServerWebExchange serverWebExchange,Integer code,String msg) {
		serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		JsonData jsonData = new JsonData(false, code, msg);
		DataBuffer buffer = null;
		try {
			byte[] bytes = JsonUtil.toJSONBytes(jsonData);
			 buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
	}


}
