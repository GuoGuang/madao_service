package com.madao.model;

import com.madao.enums.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Getter
@Setter
@NoArgsConstructor
public class Result implements Serializable {
	private boolean status;//是否成功
	private Integer code;// 返回码
	private String message;//返回信息
	private Object data;// 返回数据

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
}
