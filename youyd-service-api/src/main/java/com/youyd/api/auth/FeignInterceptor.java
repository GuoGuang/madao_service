/*
package com.youyd.api.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@Component
public class FeignInterceptor implements RequestInterceptor {

	public void apply(RequestTemplate requestTemplate){

		Map<String, Collection<String>> headers = requestTemplate.headers();
		Collection<String> strings = headers.get(HttpHeaders.AUTHORIZATION);
		if (strings != null && !strings.isEmpty()){
			Iterator<String> iterator = strings.iterator();
			String next = iterator.next();
			requestTemplate.header(HttpHeaders.AUTHORIZATION,next );
		}

	}
}*/
