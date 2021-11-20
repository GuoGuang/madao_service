package com.madao.config;

import brave.baggage.*;
import ch.qos.logback.classic.ClassicConstants;
import cn.hutool.json.JSONObject;
import com.madao.utils.security.JWTAuthentication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * 自定义全链路追踪字段
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021-11-11 00:05:03
 */
@Configuration(proxyBeanMethods = false)
public class TracerConfig {

    BaggageField requestRequestUri = BaggageField.create(ClassicConstants.REQUEST_REQUEST_URI);
    BaggageField requestQueryString = BaggageField.create(ClassicConstants.REQUEST_QUERY_STRING);
    BaggageField requestMethod = BaggageField.create(ClassicConstants.REQUEST_METHOD);
    BaggageField userId = BaggageField.create("USER-ID");


	/**
	 * 响应前响应后过滤器
	 */
    @Bean
    WebFilter tracerWebFilter(Optional<Tracer> tracer) {
        return (exchange, chain) -> {
            exchange.getResponse().beforeCommit(() -> {
                Optional.ofNullable(exchange.<String>getAttribute(ServerWebExchange.LOG_ID_ATTRIBUTE)).ifPresent(logId -> exchange.getResponse().getHeaders().add("logId", logId));
                return Mono.empty();
            });
            return chain.filter(exchange)
		            // 执行完请求后将相关traceId、spanID返回前端，方便排查
                    .doOnSubscribe(subscription -> tracer.ifPresent(t -> {
                        Optional.ofNullable(t.currentSpan()).map(Span::context).ifPresent(context -> exchange.getResponse().beforeCommit(() -> {
                            Optional.ofNullable(context.traceId()).ifPresent(traceId -> exchange.getResponse().getHeaders().add("traceId", traceId));
                            Optional.ofNullable(context.spanId()).ifPresent(spanId -> exchange.getResponse().getHeaders().add("spanId", spanId));
                            Optional.ofNullable(context.parentId()).ifPresent(parentId -> exchange.getResponse().getHeaders().add("parentId", parentId));
                            return Mono.empty();
                        }));
						// 执行请求前传播字段
                        t.createBaggage(ClassicConstants.REQUEST_REQUEST_URI, exchange.getRequest().getURI().getPath());
                        t.createBaggage(ClassicConstants.REQUEST_QUERY_STRING, exchange.getRequest().getURI().getQuery());
                        t.createBaggage(ClassicConstants.REQUEST_METHOD, exchange.getRequest().getMethodValue());
	                    String instance = exchange.getRequest().getHeaders().getFirst("INSTANCE");
	                    String authorization = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

	                    t.createBaggage("INSTANCE", instance);

	                    if (StringUtils.isNotEmpty(authorization) & StringUtils.startsWith(authorization,JWTAuthentication.BEARER)){
		                    JSONObject jsonObject = JWTAuthentication.parseJwtToClaimsAsJSONObject(authorization);
		                    t.createBaggage("USER-ID",jsonObject.getStr("user_name"));
	                    }
                    }));
        };
    }

	/**
	 * 传播字段定制器，SingleBaggageField.remote请求时携带请求头
	 */
    @Bean
    BaggagePropagationCustomizer baggagePropagationCustomizer() {
        return builder -> builder
                .add(BaggagePropagationConfig.SingleBaggageField.remote(requestRequestUri))
                .add(BaggagePropagationConfig.SingleBaggageField.remote(requestQueryString))
                .add(BaggagePropagationConfig.SingleBaggageField.remote(userId))
                .add(BaggagePropagationConfig.SingleBaggageField.remote(requestMethod));
    }

	/**
	 * 相关范围定制器
	 */
    @Bean
    CorrelationScopeCustomizer correlationScopeCustomizer() {
        return builder -> builder
                .add(CorrelationScopeConfig.SingleCorrelationField.create(BaggageFields.PARENT_ID))
                .add(CorrelationScopeConfig.SingleCorrelationField.create(BaggageFields.SAMPLED))
                .add(CorrelationScopeConfig.SingleCorrelationField.newBuilder(requestRequestUri).flushOnUpdate().build())
                .add(CorrelationScopeConfig.SingleCorrelationField.newBuilder(requestQueryString).flushOnUpdate().build())
                .add(CorrelationScopeConfig.SingleCorrelationField.newBuilder(requestMethod).flushOnUpdate().build())
                .add(CorrelationScopeConfig.SingleCorrelationField.newBuilder(userId).flushOnUpdate().build());
    }
}
