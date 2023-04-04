package com.madao.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Configuration
public class JacksonConfig {

	@Bean
	@Primary
	@ConditionalOnMissingBean(ObjectMapper.class)
	public ObjectMapper jacksonObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		// （禁止SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS）
		// 解决 No serializer found for class org.hibernate.proxy.entity.javassist.JavassistL
		// 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
		// Include.Include.ALWAYS 默认
		// Include.NON_DEFAULT 属性为默认值不序列化
		// Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量
		// Include.NON_NULL 属性为NULL 不序列化
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 允许出现特殊字符和转义符
		objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
		// 允许出现单引号
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		// 字段保留，将null值转为""
		objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
				jsonGenerator.writeString("");
			}
		});
		// 解决jackson2无法反序列化LocalDateTime的问题
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.registerModule(new JavaTimeModule());

		return objectMapper;
	}

}
