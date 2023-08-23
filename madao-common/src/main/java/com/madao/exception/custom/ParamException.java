package com.madao.exception.custom;

/**
 * 自定义 参数 异常
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class ParamException extends RuntimeException {

    public ParamException(String message) {
        super(message);
    }
}