package com.madao.enums;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public enum ProviderEnum {

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
	GITHUB(3),

	/**
	 * Phone
	 */
	PHONE(4);

	private final Integer value;

	ProviderEnum(Integer value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
