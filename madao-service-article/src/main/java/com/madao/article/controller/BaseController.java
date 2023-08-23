package com.madao.article.controller;

import com.madao.utils.security.JWTAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

import java.util.Map;

/**
 * 基础控制器，其他控制器需继承该控制器
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public interface BaseController {

	default Map<String, String> getUserInfo(HttpServletRequest request) {
		String fullAuthorization = JWTAuthentication.getFullAuthorization(request.getHeader(HttpHeaders.AUTHORIZATION));
		return JWTAuthentication.parseJwtToClaims(fullAuthorization);
	}

}
