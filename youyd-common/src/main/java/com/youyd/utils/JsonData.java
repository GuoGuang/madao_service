package com.youyd.utils;

import com.youyd.enums.StatusEnum;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * 通用json返回类型
 * @author Administrator
 * @param <E>
 *
 */

@ApiModel("api接口通用返回对象")
public class JsonData<T> implements Serializable {

	private boolean status;

	private int code;

	private String message;

	private T data;

	/**
	 * 快速创建成功结果并返回结果数据
	 *
	 * @param isSuccess
	 * @return Result
	 */
	public static JsonData success(Boolean isSuccess) {
		return new JsonData(isSuccess, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
	}
	public static JsonData error(Boolean isSuccess) {
		return new JsonData(isSuccess, StatusEnum.ERROR.getCode(), StatusEnum.ERROR.getMsg(),null);
	}

	public JsonData(StatusEnum statusEnum) {
		this(false,statusEnum.getCode(),statusEnum.getMsg());
	}


	public JsonData(boolean status, int code, String message, T data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public JsonData(boolean state, Integer code, String msg) {
		this.status = state;
		this.code = code;
		this.message = msg;
	}
	public JsonData() {

	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
