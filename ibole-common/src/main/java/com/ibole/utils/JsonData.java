package com.ibole.utils;

import com.ibole.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 通用json返回类型
 * @author Administrator
 * @param <E>
 *
 */

@ApiModel("api接口通用返回对象")
@Getter
@Setter
@NoArgsConstructor
public class JsonData<T> implements Serializable {

	private boolean status;

	private int code;

	private String message;

	private T data;

	public JsonData(boolean state, StatusEnum statusEnum) {
		this(state,statusEnum.getCode(), statusEnum.getMsg());
	}

	public JsonData(boolean state, StatusEnum statusEnum, String msg) {
		this(state,statusEnum.getCode(),msg);
	}

	/**
	 * 快速创建成功结果并返回结果数据
	 *
	 * @param data
	 * @return Result
	 */
	public static JsonData success(Object data) {
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),data);
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

	public static boolean isSuccess(JsonData jsonData) {
		return jsonData.status && jsonData.code == StatusEnum.OK.getCode();
	}

}
