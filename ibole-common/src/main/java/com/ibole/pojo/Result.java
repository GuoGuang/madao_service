package com.ibole.pojo;

import com.ibole.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 返回结果类
 **/
@Getter
@Setter
public class Result implements Serializable {
	private boolean status;//是否成功
	private Integer code;// 返回码
	private String message;//返回信息
	private Object data;// 返回数据

	public Result() {
	}

	public Result(StatusEnum statusEnum) {
		this(false,statusEnum.getCode(), statusEnum.getMsg());
	}


	public Result(boolean status, Integer code, String message, Object data) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public Result(boolean status, Integer code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
	}

}
