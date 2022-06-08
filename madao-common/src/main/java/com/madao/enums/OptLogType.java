package com.madao.enums;

/**
 * OptLog status.
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public enum OptLogType {

	/**
	 * Add status
	 */
	ADD(0),

	/**
	 * Delete status.
	 */
	DELETE(1),

	/**
	 * Modify status
	 */
	MODIFY(2);

	private final Integer value;

	OptLogType(Integer value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
