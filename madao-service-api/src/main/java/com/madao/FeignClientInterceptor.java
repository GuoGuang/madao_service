package com.madao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 扩展 Feign  get传输pojo，Feign调用携带请求头
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 * @see https://stackoverflow.com/questions/53546840/spring-cloud-openfeign-failed-to-create-dynamic-query-parameters
 */
@Slf4j
@Configuration
public class FeignClientInterceptor implements RequestInterceptor {

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
				log.error("IOException occurred while try to create http query", e);
			}
		}

		// Feign调用携带请求头
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes != null) {
			HttpServletRequest request = attributes.getRequest();
			Enumeration<String> headerNames = request.getHeaderNames();
			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					String name = headerNames.nextElement();
					//  ignore content-length，otherwise threw too many bytes written executing
					if ("content-length".equalsIgnoreCase(name)) {
						continue;
					}
					template.header(name, request.getHeader(name));
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