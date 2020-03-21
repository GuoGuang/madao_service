package com.codeway;

import com.codeway.utils.LogBack;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * 扩展 Feign  get传输pojo
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
				LogBack.error("IOException occurred while try to create http query");
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