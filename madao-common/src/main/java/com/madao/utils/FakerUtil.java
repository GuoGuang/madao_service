package com.madao.utils;

import com.github.javafaker.Faker;

/**
 * Faker 数据模拟
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
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
