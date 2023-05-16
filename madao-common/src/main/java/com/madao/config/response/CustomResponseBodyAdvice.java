package com.madao.config.response;

import com.madao.model.BasePojo;
import com.madao.utils.ClassUtil;
import com.madao.utils.JsonData;
import com.madao.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 处理响应相关逻辑
 */
@Slf4j
@Configuration
@ControllerAdvice
@AllArgsConstructor
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<JsonData<Page>> {

    public boolean supports(MethodParameter returnType, @NonNull Class converterType) {
        return ClassUtil.isAnnotated(returnType.getMethod(), CustomResponseBody.class);
    }

    @Override
    public JsonData<Page> beforeBodyWrite(JsonData<Page> body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return null;
        } else {
            CustomResponseBody incasSteMdeMdtResponseBody = ClassUtil.getAnnotation(returnType.getMethod(), CustomResponseBody.class);
            for (BasePojo record :JsonUtil.jsonToListPojo(JsonUtil.toJsonString(body.getData()), BasePojo.class)) {
                log.info("------------{}", record);
            }
            return body;
        }
    }
}