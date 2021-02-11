package com.madao.auth.validate.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 验证码信息封装类
 **/
public class ValidateCode implements Serializable {

    private String code;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public ValidateCode() {

    }

    @JsonIgnore
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

	public String getCode() {
		return code;
	}

	public ValidateCode setCode(String code) {
		this.code = code;
		return this;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public ValidateCode setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
		return this;
	}
}
