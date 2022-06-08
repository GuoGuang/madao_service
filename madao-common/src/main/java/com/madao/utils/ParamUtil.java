package com.madao.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 参数校验
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class ParamUtil {

	/**
	 * Utility classes should not have public constructors
	 */
	private ParamUtil() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * 验证参数有效性
	 *
	 * @param map
	 * @param params: 一个或者多个参数, 适用于 逻辑与
	 * @return boolean
	 */
	public static Boolean isAvailable(Map map, Object... params) {
		for (Object param : params) {
			if (map.get(param) == null || StringUtils.isBlank(map.get(param).toString())) {
				return false;
			}
		}
		return true;
	}
}
