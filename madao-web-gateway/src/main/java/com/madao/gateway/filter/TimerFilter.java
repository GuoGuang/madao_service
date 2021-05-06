package com.madao.gateway.filter;

import com.madao.utils.LogBack;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.NumberFormat;

/**
 * GlobalFilter timer
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Component
public class TimerFilter implements GlobalFilter, Ordered {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		CustomMsStopWatch timer = new CustomMsStopWatch();
		timer.start(exchange.getRequest().getURI().getPath());

		// call back after the request is executed
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
					timer.stop();
					LogBack.info(timer.prettyPrint());
				})
		);
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

}

class CustomMsStopWatch extends StopWatch {
	@Override
	public String prettyPrint() {
		StringBuilder sb = new StringBuilder(shortSummary());
		sb.append('\n');
		sb.append("---------------------------------------------\n");
		sb.append("ms         %     Task name\n");
		sb.append("---------------------------------------------\n");
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMinimumIntegerDigits(9);
		nf.setGroupingUsed(false);
		NumberFormat pf = NumberFormat.getPercentInstance();
		pf.setMinimumIntegerDigits(3);
		pf.setGroupingUsed(false);
		for (TaskInfo task : getTaskInfo()) {
			sb.append(nf.format(task.getTimeMillis())).append("  ");
			sb.append(pf.format((double) task.getTimeNanos() / getTotalTimeNanos())).append("  ");
			sb.append(task.getTaskName()).append("\n");
		}
		return sb.toString();
	}
}
