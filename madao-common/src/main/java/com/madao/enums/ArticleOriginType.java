package com.madao.enums;

/**
 * Article status.
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
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
