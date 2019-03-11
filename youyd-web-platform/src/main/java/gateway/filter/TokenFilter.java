package gateway.filter;

import com.youyd.utils.LogBack;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/** https://windmt.com/2018/05/08/spring-cloud-14-spring-cloud-gateway-filter/
 * @description: GateWay全局过滤器
 * @author: LGG
 * @create: 2018-12-28
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
		/*String token = exchange.getRequest().getHeaders().getFirst("x-token");
		String path = exchange.getRequest().getURI().getPath();
		if ((token == null || token.isEmpty()) && !path.contains("login")) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}*/
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 100;
	}
}
