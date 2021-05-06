package com.madao.exception.custom;

/**
 * 用户相关异常
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
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
