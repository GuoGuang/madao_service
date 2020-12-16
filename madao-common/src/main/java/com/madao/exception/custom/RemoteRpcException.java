package com.madao.exception.custom;

import com.madao.utils.JsonData;
import com.madao.utils.JsonUtil;

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
