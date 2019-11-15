package com.ibole.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 参数校验
 **/
public class ParamUtil {

	/**
	 * 验证参数有效性
	 * @param map
	 * @param params: 一个或者多个参数, 适用于 逻辑与
	 * @return boolean
	 */
	public static Boolean isAvailable(Map map,Object... params){
		for (Object param : params) {
			if (map.get(param) == null || StringUtils.isBlank(map.get(param).toString())){
				return false;
			}
		}
		return true;
	}


	/**
	 * Utility classes should not have public constructors
	 */
	private ParamUtil(){
			throw new IllegalStateException("Utility class");
		}
}
