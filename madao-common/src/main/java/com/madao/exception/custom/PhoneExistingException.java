package com.madao.exception.custom;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class PhoneExistingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PhoneExistingException() {
        super("手机号已存在！");
    }

}
