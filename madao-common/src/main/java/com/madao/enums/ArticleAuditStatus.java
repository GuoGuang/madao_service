package com.madao.enums;

/**
 * Article audit status.
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
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
