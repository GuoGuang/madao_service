package com.ibole.exception.custom;

import com.ibole.utils.JsonData;
import com.ibole.utils.JsonUtil;

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
