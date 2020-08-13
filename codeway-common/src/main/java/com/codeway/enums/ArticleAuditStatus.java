package com.codeway.enums;

import lombok.Getter;

/**
 * Article audit status.
 *
 * @author GuoGuang
 */
@Getter
public enum ArticleAuditStatus {

	/**
	 * Pass status
	 */
	PASS(1),

	/**
	 * Auditing status.
	 */
	AUDITING(2),

	/**
	 * Refuse status
	 */
	REFUSE(3);

	private final Integer value;

	ArticleAuditStatus(Integer value) {
		this.value = value;
	}

}
