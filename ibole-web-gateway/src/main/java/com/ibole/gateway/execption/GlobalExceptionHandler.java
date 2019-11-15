package com.ibole.gateway.execption;

import com.ibole.enums.StatusEnum;
import com.ibole.utils.JsonData;
import com.ibole.utils.JsonUtil;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;

import java.util.Map;

/**
 *  Gateway自定义异常处理
 * @author GuoGuang
 *
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
	protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
		boolean isJson = JsonUtil.isJson(super.getError(request).getMessage());
		if (isJson){
			Map<String, Object> errorAttributes = JsonUtil.jsonToMap(super.getError(request).getMessage());
			return errorAttributes;
		}else {
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
	 * @param errorAttributes
	 */
	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}

	/**
	 * 根据code获取对应的HttpStatus
	 * @param errorAttributes
	 */
	@Override
	protected HttpStatus getHttpStatus(Map<String, Object> errorAttributes) {
		return HttpStatus.valueOf(500);
	}



}

