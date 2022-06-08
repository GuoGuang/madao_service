package com.madao.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class CookieUtil {

	/**
	 * 设置cookie
	 *
	 * @param response
	 * @param name     cookie名字
	 * @param value    cookie值
	 * @param maxAge   cookie生命周期 以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String domain, String path, String name,
	                             String value, int maxAge, boolean httpOnly) {
		Cookie cookie = new Cookie(name, value);
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		cookie.setHttpOnly(httpOnly);
		response.addCookie(cookie);
	}


	/**
	 * 根据cookie名称读取cookie
	 *
	 * @param request
	 * @param cookieName1,cookieName2
	 * @return map<cookieName, cookieValue>
	 */

	public static Map<String, String> readCookie(HttpServletRequest request, String... cookieNames) {
		Map<String, String> cookieMap = new HashMap<String, String>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				String cookieValue = cookie.getValue();
				for (int i = 0; i < cookieNames.length; i++) {
					if (cookieNames[i].equals(cookieName)) {
						cookieMap.put(cookieName, cookieValue);
					}
				}
			}
		}
		return cookieMap;

	}
}
