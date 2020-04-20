package com.codeway.exception.custom;

import com.codeway.utils.JsonData;
import com.codeway.utils.JsonUtil;

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
