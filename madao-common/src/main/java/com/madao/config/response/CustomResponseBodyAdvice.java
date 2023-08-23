package com.madao.config.response;

import com.madao.model.BasePojo;
import com.madao.utils.ClassUtil;
import com.madao.utils.JsonData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * 处理响应相关逻辑
 */
@Slf4j
@Configuration
@ControllerAdvice
@AllArgsConstructor
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<JsonData<Page<? extends BasePojo>>> {

//	@Autowired
//	IAttachClient attachClient;

    public boolean supports(MethodParameter returnType, @NonNull Class converterType) {
        return ClassUtil.isAnnotated(returnType.getMethod(), CustomResponseBody.class);
    }

    @Override
    public JsonData<Page<? extends BasePojo>> beforeBodyWrite(JsonData<Page<? extends BasePojo>> body, @NotNull MethodParameter returnType, @NotNull MediaType selectedContentType, @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        if (body == null) {
            return null;
        } else {
            // 处理分页查询接口，获取请求参数
            String menuId = ((ServletServerHttpRequest) request).getServletRequest().getParameter("menuId");
            Page<? extends BasePojo> data = body.getData();
            List<? extends BasePojo> items = data.getContent();
            if (StringUtils.isNotBlank(menuId)) {
                // 处理业务逻辑
            }
        }
        return body;
    }
}
