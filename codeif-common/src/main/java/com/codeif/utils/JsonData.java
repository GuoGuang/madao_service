package com.codeif.utils;

import com.codeif.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
