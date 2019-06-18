package com.youyd.auth.controller;

import com.youyd.auth.service.AuthService;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.user.AuthToken;
import com.youyd.pojo.user.User;
import com.youyd.utils.CookieUtil;
import com.youyd.utils.JsonData;
import com.youyd.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录认证
 * @author : LGG
 * @create : 2019-06-18 14:34
 **/
@RestController
@RequestMapping("/auth")
public class AuthController  {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public JsonData login(@RequestBody User user, HttpServletRequest request) {
        if(user == null || StringUtils.isEmpty(user.getUserName())){
	        // TODO 异常
	        throw new RuntimeException();
//            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        if(user == null || StringUtils.isEmpty(user.getPassword())){
	        // TODO 异常
	        throw new RuntimeException();
        }
        //账号
        String username = user.getUserName();
        //密码
        String password = user.getPassword();

        //申请令牌
	    AuthToken authToken = null;
	    try {
		    authToken = authService.login(username,password,clientId,clientSecret,request);
	    } catch (Exception e) {
		    e.printStackTrace();
		    JsonData jsonData = JsonUtil.jsonToPojo(e.getMessage(), JsonData.class);
		    return jsonData;
	    }

	    //用户身份令牌
        String access_token = authToken.getAccess_token();
        //将令牌存储到cookie
        saveCookie(access_token);
	    return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), access_token);

    }

    //将令牌存储到cookie
    private void saveCookie(String token){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",token,cookieMaxAge,false);
    }

	@PostMapping(value = "/logout")
	//@OptLog(operationType= CommonConst.MODIFY,operationName="退出系统")
    public JsonData logout(@RequestHeader("AUTH")String token) {
		authService.logout(token);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
    }
}
