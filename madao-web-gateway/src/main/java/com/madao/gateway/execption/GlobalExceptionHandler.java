package com.madao.gateway.execption;

import com.madao.enums.StatusEnum;
import com.madao.utils.JsonData;
import com.madao.utils.JsonUtil;
import com.madao.utils.LogBack;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.server.*;

import java.util.Map;

/**
 * Gateway自定义异常处理
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class GlobalExceptionHandler extends DefaultErrorWebExceptionHandler {

	public GlobalExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
	                              ErrorProperties errorProperties, ApplicationContext applicationContext) {
		super(errorAttributes, resourceProperties, errorProperties, applicationContext);
	}

	/**
	 * 获取异常属性
	 */
	@Override
	protected Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
		LogBack.error("Gateway获取自定义异常--------->{}", super.getError(request).getMessage());
		boolean isJson = JsonUtil.isJson(super.getError(request).getMessage());
		if (isJson) {
			Map<String, Object> errorAttributes = JsonUtil.jsonToMap(super.getError(request).getMessage());
			return errorAttributes;
		} else {
			JsonData<Object> objectJsonData = new JsonData<>(false,
					StatusEnum.SYSTEM_ERROR.getCode(),
					super.getError(request).getMessage());
			String toJsonString = JsonUtil.toJsonString(objectJsonData);
			Map<String, Object> maps = JsonUtil.jsonToMap(toJsonString);
			return maps;
		}
	}


	/**
	 * 指定响应处理方法为JSON处理的方法
	 *
	 * @param errorAttributes
	 */
	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}

	/**
	 * 根据code获取对应的HttpStatus
	 */
	@Override
	protected int getHttpStatus(Map<String, Object> errorAttributes) {
		return 500;
	}


}

