package com.madaoo.exception.custom;

import com.madaoo.utils.JsonData;
import com.madaoo.utils.JsonUtil;

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
