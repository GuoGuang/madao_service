package com.codeway.exception.custom;

public class PhoneExistingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PhoneExistingException() {
		super("手机号已存在！");
	}

}
