package com.madao.exception.custom;

public class ThreadException extends RuntimeException {

	public ThreadException(String message) {
		super(message);
	}

	public ThreadException() {
		super("线程异常！");
	}
}
