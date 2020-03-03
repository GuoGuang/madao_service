package com.codeif.exception.custom;

import com.codeif.utils.JsonData;
import com.codeif.utils.JsonUtil;

/**
 * 自定义异常：RemoteRpcException异常
 **/
public class RemoteRpcException extends RuntimeException {

	public RemoteRpcException(JsonData jsonData) {
		super(JsonUtil.toJsonString(jsonData));
	}
	public RemoteRpcException() {
		super();
	}

}
