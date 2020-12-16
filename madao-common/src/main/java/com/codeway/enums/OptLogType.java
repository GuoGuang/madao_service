package com.madaoo.enums;

import lombok.Getter;

/**
 * OptLog status.
 *
 * @author GuoGuang
 */
@Getter
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

}
