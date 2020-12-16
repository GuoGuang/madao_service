package gateway.filter;

import com.madaoo.utils.LogBack;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/** https://windmt.com/2018/05/08/spring-cloud-14-spring-cloud-gateway-filter/
 * GateWay全局过滤器
 **/

@Component
public class TokenFilter implements GlobalFilter, Ordered {

	/**
	 * 获取token字段，如果能获取到就 pass，获取不到就直接返回401错误，
	 * chain.filter(exchange)之前的就是 “pre” 部分，之后的也就是then里边的是 “post” 部分
	 * @param exchange
	 * @param chain
	 * @return
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		LogBack.info("进入GateWay-TokenFilter");
		String token = exchange.getRequest().getHeaders().getFirst("X-TOKEN");
		String path = exchange.getRequest().getURI().getPath();
		//向headers中设置头
		ServerHttpRequest Stoken = exchange.getRequest().mutate().header("X-TOKEN", token).build();
		//将现在的request 变成 change对象
		ServerWebExchange build = exchange.mutate().request(Stoken).build();
		return chain.filter(build);
	}

	@Override
	public int getOrder() {
		return 100;
	}
}
