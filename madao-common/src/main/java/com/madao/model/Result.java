package com.madao.model;

import com.madao.enums.StatusEnum;

import java.io.Serializable;

public class Result implements Serializable {
    private boolean status;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private Object data;// 返回数据

    public Result() {
    }

    public Result(StatusEnum statusEnum) {
        this(false, statusEnum.getCode(), statusEnum.getMsg());
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

	public boolean isStatus() {
		return status;
	}

	public Result setStatus(boolean status) {
		this.status = status;
		return this;
	}

	public Integer getCode() {
		return code;
	}

	public Result setCode(Integer code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Result setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getData() {
		return data;
	}

	public Result setData(Object data) {
		this.data = data;
		return this;
	}
}
