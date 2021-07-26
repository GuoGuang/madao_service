package com.madao.utils;

import com.madao.enums.StatusEnum;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@ApiModel("api接口通用返回对象")
public class JsonData<T> implements Serializable {

    private boolean status;

    private int code;

    private String message;

    private T data;

    public JsonData(boolean state, StatusEnum statusEnum) {
        this(state, statusEnum.getCode(), statusEnum.getMsg());
    }
    public JsonData() {
    }

    public JsonData(boolean state, StatusEnum statusEnum, String msg) {
        this(state, statusEnum.getCode(), msg);
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static <T> JsonData<T> success(T data) {
        return new JsonData<>(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), data);
    }

    public static <T> JsonData<T> success() {
        return new JsonData<>(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
    }

    public static <T> JsonData<T> failed(StatusEnum statusEnum) {
        return new JsonData<>(false, statusEnum.getCode(), statusEnum.getMsg(), null);
    }

    public static <T> JsonData<T> failed(Exception exception) {
        return new JsonData<>(false, StatusEnum.ERROR.getCode(), exception.getMessage(), null);
    }

    public static <T> JsonData<T> failed(StatusEnum statusEnum, String customMsg) {
        return new JsonData<>(false, statusEnum.getCode(), customMsg, null);
    }

    public JsonData(StatusEnum statusEnum) {
        this(false, statusEnum.getCode(), statusEnum.getMsg());
    }
	public JsonData(StatusEnum statusEnum,String extMessage) {
		this(false, statusEnum.getCode(), statusEnum.getMsg() + extMessage);
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

	public boolean isStatus() {
		return status;
	}

	public JsonData<T> setStatus(boolean status) {
		this.status = status;
		return this;
	}

	public int getCode() {
		return code;
	}

	public JsonData<T> setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public JsonData<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public T getData() {
		return data;
	}

	public JsonData<T> setData(T data) {
		this.data = data;
		return this;
	}
}
