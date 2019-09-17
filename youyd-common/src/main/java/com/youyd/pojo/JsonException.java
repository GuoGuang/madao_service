package com.youyd.pojo;

/**
 * 自定义异常：Json序列化异常
 * @author : LGG
 * @create : 2019-06-18 14:34
 **/
public class JsonException extends RuntimeException {

	public JsonException(String jsonData) {
		super(jsonData);
	}

}
