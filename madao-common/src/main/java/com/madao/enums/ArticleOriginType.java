package com.madao.enums;



/**
 * Post status.
 *
 * @author GuoGuang
 */
public enum ArticleOriginType {

    /**
     * Original status.
     */
    ORIGINAL(0),

    /**
     * Reprint status.
     */
    REPRINT(1),

    /**
     * Mixed status.
     */
    MIXED(2);

    private final int value;

    ArticleOriginType(int value) {
        this.value = value;
    }

	public int getValue() {
		return value;
	}
}
