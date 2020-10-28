package com.codeway.gateway.filter;

import com.codeway.enums.StatusEnum;
import com.codeway.gateway.service.AuthService;
import com.codeway.model.dto.user.AuthToken;
import com.codeway.utils.JsonData;
import com.codeway.utils.JsonUtil;
import com.codeway.utils.LogBack;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 *  GateWay全局过滤器
 * @see http://www.manongjc.com/article/43669.html
 * @see https://windmt.com/2018/05/08/spring-cloud-14-spring-cloud-gateway-filter/
 **/

@Component
public class TokenFilter implements GlobalFilter, Ordered {


	private static final String X_CLIENT_TOKEN_USER = "x-client-token-user";
	private static final String X_CLIENT_TOKEN = "x-client-token";
	private static final String BEARER = "Bearer ";


	/**
	 * 由authentication-client模块提供签权的feign客户端
	 */
	@Autowired
	private AuthService authService;

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
		if (authService.ignoreAuthentication(url) || match(url,
				"/oauth/token",
				"/api/oauth/token",
				"/oauth/code/sms",
				"/api/oauth/login/github",
				"/api/oauth2/**",
				"/api/login/**",
				"/api/bilibili/list",
				"/oauth/code/captcha",
				"/api/oauth/code/captcha",
				"/api/su/register",
				"/api/ar/tag",
				"/api/ba/music/**",
				"/api/ba/announcement",
				"/api/article",
				"/api/su/admin/repo",
				"/api/su/admin",
				"/api/movie",
				"/api/option",
				"/api/expansion/constant",
				"/api/events",
				"/api/like/site",
				"/api/ar/comment/like",
				"/api/ar/comment/user",
				"/api/ar/comment/**",
				"/api/ar/article/category",
				"/api/ar/article/like",
				"/api/ar/article/**",
				"/api/ar/article/hot",
				"/api/ar/article/tag",
				"/api/ba/announcement"
		)) {
			return chain.filter(exchange).doFinally((r) -> System.out.printf("[%s]---->请求结束, 响应码--->[%s]",
					exchange.getRequest().getURI().getPath(),
					exchange.getResponse().getStatusCode()));
		}
		// 如果请求未携带token信息, 直接跳出
		if (StringUtils.isBlank(authentication) || !authentication.contains(BEARER)) {
			LogBack.error("url:{},method:{},headers:{}, 请求未携带token信息", url, method, request.getHeaders());
			return unAuthorized(exchange, StatusEnum.PARAM_ILLEGAL);
		}

		long expire = authService.getExpire(authentication);
		// 过期
		if(expire<0){
			return unAuthorized(exchange,StatusEnum.LOGIN_EXPIRED);
		}

		AuthToken authToken = authService.getAuthToken(authentication);
		String jwtToken = authToken.getAccess_token();
		//调用签权服务看用户是否有权限，若有权限进入下一个filter
		if (authService.commonAuthentication(url) || authService.hasPermission(jwtToken, url, method) ) {
			ServerHttpRequest.Builder builder = request.mutate();
			builder.header(X_CLIENT_TOKEN, "TODO 添加服务间简单认证");//TODO 转发的请求都加上服务间认证token
			//将jwt token中的用户信息传给服务
			builder.header(X_CLIENT_TOKEN_USER, authService.getJwt(jwtToken).getClaims());
			builder.header(HttpHeaders.AUTHORIZATION,BEARER+jwtToken);
			return chain.filter(exchange.mutate().request(builder.build()).build());
		}
		return unAuthorized(exchange,StatusEnum.UN_AUTHORIZED);
	}


	@Override
	public int getOrder() {
		return -100;
	}


	/**
	 * 网关拒绝，返回401
	 */
	private Mono<Void> unAuthorized(ServerWebExchange serverWebExchange,StatusEnum statusEnum) {
		ServerHttpResponse response = serverWebExchange.getResponse();
		response.setStatusCode(response.getStatusCode());
		response.getHeaders().add("Content-Type",MediaType.APPLICATION_JSON_VALUE);
		JsonData<Object> jsonData = new JsonData<>(statusEnum);
		DataBuffer buffer = null;
		try {
			byte[] bytes = JsonUtil.toJSONBytes(jsonData);
			buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
		} catch (IOException e) {
			LogBack.error(e.getMessage(), e);
		}

		return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
	}

	/**
	 * 匹配资料
	 *
	 * @param patternPath 模糊匹配表达式
	 * @param requestPath 待匹配的url
	 * @return
	 */
	private static boolean match(String requestPath, String... patternPath) {
		PathMatcher matcher = new AntPathMatcher();
		if (ArrayUtils.isNotEmpty(patternPath)) {
			for (String pt : patternPath) {
				if (matcher.match(pt, requestPath)) {
					return true;
				}
			}
		}
		return false;
	}

}
