package com.madao.exception.custom;

/**
 * 用户相关异常
 */
public class UserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserException(String message) {
        super(message);
    }

    public UserException() {
        super();
    }
}
