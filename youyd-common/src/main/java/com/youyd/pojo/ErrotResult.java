/*
package com.youyd.pojo;

import com.youyd.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

*/
/**
 * @description 返回异常结果类
 * @author LGG
 * @create 2019-05-27
 **//*

@Getter
@Setter
public class ErrotResult implements Serializable {
	private boolean status = false;
	private Integer code;// 返回码
	private String message;//返回信息
	private String detailMsg;//返回详细信息

	public ErrotResult() {
	}

	public ErrotResult(StatusEnum statusEnum) {
		this(statusEnum.getCode(), statusEnum.getMsg(),"");
	}
	public ErrotResult(StatusEnum statusEnum,String detailMsg) {
		this(statusEnum.getCode(), statusEnum.getMsg(),detailMsg);
	}


	public ErrotResult(Integer code, String message, String detailMsg) {
		super();
		this.code = code;
		this.message = message;
		this.detailMsg = detailMsg;
	}
	public ErrotResult(boolean status, Integer code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
	}

}
*/
