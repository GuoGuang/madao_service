package com.youyd;

import com.youyd.utils.JsonData;
import com.youyd.utils.JsonUtil;

/**
 * 自定义异常：RemoteRpcException异常
 * @author : LGG
 * @create : 2019-08-04 14:34
 **/
public class RemoteRpcException extends RuntimeException {

	public RemoteRpcException(JsonData jsonData) {
		super(JsonUtil.toJsonString(jsonData));
	}
	public RemoteRpcException() {
		super();
	}

}
