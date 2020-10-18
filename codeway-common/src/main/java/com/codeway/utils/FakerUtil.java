package com.codeway.utils;

import com.github.javafaker.Faker;

/**
 * Faker 数据模拟
 */
public class FakerUtil {

	public static String getAvatar() {
		Faker faker = new Faker();
		return faker.avatar().image();
	}

	public static String getNickName() {
		Faker faker = new Faker();
		return faker.name().lastName();
	}

	private FakerUtil() {

	}
}
