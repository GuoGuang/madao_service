package com.madao.model.pojo;

public class JsonException extends RuntimeException {

    public JsonException(String jsonData) {
        super(jsonData);
    }

}
