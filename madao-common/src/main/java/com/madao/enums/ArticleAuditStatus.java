package com.madao.enums;

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

}
