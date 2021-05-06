package com.madao.enums;


/**
 * Article audit status.
 *
 * @author GuoGuang
 */
public enum ArticleAuditStatus {

    /**
     * Pass status
     */
    PASS(0),

    /**
     * Auditing status.
     */
    AUDITING(1),

    /**
     * Refuse status
     */
    REFUSE(2);

    private final Integer value;

    ArticleAuditStatus(Integer value) {
        this.value = value;
    }

	public Integer getValue() {
		return value;
	}
}
