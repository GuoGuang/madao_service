package com.codeway.enums;

import lombok.Getter;

@Getter
public enum RegisteredType {

	QQ(1),

	WE_CHAT(2),

	GITHUB(3);

	private final Integer value;

	RegisteredType(Integer value) {
		this.value = value;
	}

}
