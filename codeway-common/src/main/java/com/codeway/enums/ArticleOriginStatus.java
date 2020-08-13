package com.codeway.enums;

import lombok.Getter;

/**
 * Post status.
 *
 * @author GuoGuang
 */
@Getter
public enum ArticleOriginStatus {

	/**
	 * Original status.
	 */
	ORIGINAL(1),

	/**
	 * Reprint status.
	 */
	REPRINT(2),

	/**
	 * Mixed status.
	 */
	MIXED(3);

	private final int value;

	ArticleOriginStatus(int value) {
		this.value = value;
	}

}
