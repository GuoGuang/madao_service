package com.youyd;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youyd.utils.LogBack;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * 扩展 Feign  get传输pojo
 * @author : LGG
 * @create : 2019-06-11
 * @see https://stackoverflow.com/questions/53546840/spring-cloud-openfeign-failed-to-create-dynamic-query-parameters
 **/
@Configuration
public class FeignConfiguration implements RequestInterceptor {
	private final Logger logger = LoggerFactory.getLogger(getClass());


	@Resource
	private ObjectMapper objectMapper;

	@Override
	public void apply(RequestTemplate template) {
		if (HttpMethod.GET.name().equals(template.method())
				&& null != template.body()) {
			try {
				JsonNode jsonNode = objectMapper.readTree(template.body());
				template.body(null);

				Map<String, Collection<String>> queries = new HashMap<>();
				buildQuery(jsonNode, "", queries);
				template.queries(queries);
			} catch (IOException e) {
				LogBack.error("【拦截GET请求POJO方式】-出错了：{}", ExceptionUtils.getStackFrames(e));
				throw new RuntimeException();
			}
		}
	}

	private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {
		// 叶子节点
		if (!jsonNode.isContainerNode()) {
			if (jsonNode.isNull()) {
				return;
			}
			Collection<String> values = queries.get(path);
			if (null == values) {
				values = new ArrayList<>();
				queries.put(path, values);
			}
			values.add(jsonNode.asText());
			return;
		}
		// 数组节点
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
				} else {  // 根节点
					buildQuery(entry.getValue(), entry.getKey(), queries);
				}
			}
		}
	}
}