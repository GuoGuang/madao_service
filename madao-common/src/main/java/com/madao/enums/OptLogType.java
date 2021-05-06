package com.madao.enums;



/**
 * OptLog status.
 *
 * @author GuoGuang
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
