package com.codeway.enums;

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
	ADD(1),

	/**
	 * Delete status.
	 */
	DELETE(2),

	/**
	 * Modify status
	 */
	MODIFY(3);

	private final Integer value;

	OptLogType(Integer value) {
		this.value = value;
	}

}
