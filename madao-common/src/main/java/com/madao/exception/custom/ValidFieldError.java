package com.madao.exception.custom;

import org.springframework.validation.FieldError;

import java.io.Serializable;

/**
 * 实体校验未通过类
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class ValidFieldError implements Serializable {

    private String field;
    private String message;
    private String code;

    public ValidFieldError(FieldError fieldError) {
        setCode(fieldError.getDefaultMessage());
        setField(fieldError.getField());
        setMessage(fieldError.getDefaultMessage());
    }

    public String getField() {
        return field;
    }

    private void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "参数" + field + "校验未通过：" + message;
    }


}
