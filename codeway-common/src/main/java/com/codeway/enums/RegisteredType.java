package com.codeway.enums;

import lombok.Getter;

@Getter
public enum RegisteredType {

	/**
	 * QQ
	 */
	QQ(1),

	/**
	 * 微信
	 */
	WE_CHAT(2),

	/**
	 * GITHUB
	 */
	GITHUB(3);

	private final Integer value;

	RegisteredType(Integer value) {
		this.value = value;
	}

}
