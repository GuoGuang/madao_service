package com.madao.model.entity;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class JsonException extends RuntimeException {

	public JsonException(Exception ex) {
		super(ex);
	}

}
