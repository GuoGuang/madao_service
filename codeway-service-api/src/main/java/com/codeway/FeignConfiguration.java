package com.madaoo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madaoo.utils.LogBack;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 扩展 Feign  get传输pojo
 * Feign调用携带请求头
 *
 * @see https://stackoverflow.com/questions/53546840/spring-cloud-openfeign-failed-to-create-dynamic-query-parameters
 **/
@Configuration
public class FeignConfiguration implements RequestInterceptor {

	private static final String EMPTY = "";
	@Resource
	private ObjectMapper objectMapper;

	@Override
	public void apply(RequestTemplate template) {
		if ("GET".equals(template.method()) && Objects.nonNull(template.requestBody().asBytes())) {
			try {
				JsonNode jsonNode = objectMapper.readTree(template.requestBody().asBytes());
				template.body((String) null);

				Map<String, Collection<String>> queries = new HashMap<>();
				buildQuery(jsonNode, EMPTY, queries);
				template.queries(queries);
			} catch (IOException e) {
				LogBack.error("IOException occurred while try to create http query", e);
			}
		}

		// Feign调用携带请求头
		ServletRequestAttributes attributes = (ServletRequestAttributes)
				RequestContextHolder.getRequestAttributes();
		if (attributes != null) {
			HttpServletRequest request = attributes.getRequest();
			Enumeration<String> headerNames = request.getHeaderNames();
			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					String name = headerNames.nextElement();
					// 只转发Authorization头
					// https://blog.csdn.net/qq_39986681/article/details/107138740
					if (HttpHeaders.AUTHORIZATION.equalsIgnoreCase(name)) {
						String values = request.getHeader(name);
						template.header(name, values);
					}

				}
			}
		}


	}

	private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {
		if (!jsonNode.isContainerNode()) {
			if (jsonNode.isNull()) {
				return;
			}
			Collection<String> values = queries.computeIfAbsent(path, k -> new ArrayList<>());
			values.add(jsonNode.asText());
			return;
		}
		if (jsonNode.isArray()) {
			Iterator<JsonNode> it = jsonNode.elements();
			while (it.hasNext()) {
				buildQuery(it.next(), path, queries);
			}
		} else {
			Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
			while (it.hasNext()) {
				Map.Entry<String, JsonNode> entry = it.next();
				if (StringUtils.hasText(path)) {
					buildQuery(entry.getValue(), path + "." + entry.getKey(), queries);
				} else {
					buildQuery(entry.getValue(), entry.getKey(), queries);
				}
			}
		}
	}
}