package com.madaoo.article.controller;


import com.madaoo.utils.security.JWTAuthentication;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 基础控制器，其他控制器需继承该控制器
 *
 * @author Vincent
 */
public interface BaseController {

	default Map<String, String> getUserInfo(HttpServletRequest request){
		String fullAuthorization = JWTAuthentication.getFullAuthorization(request.getHeader(HttpHeaders.AUTHORIZATION));
		Map<String, String> stringStringMap = JWTAuthentication.parseJwtToClaims(fullAuthorization);
		return stringStringMap;
	}
       
}
