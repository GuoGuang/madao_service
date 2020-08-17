package com.codeway.model.pojo;

/**
 * 自定义异常：Json序列化异常
 **/
public class JsonException extends RuntimeException {

	public JsonException(String jsonData) {
		super(jsonData);
	}

}
