package com.madao.auth.filter;

import com.madao.auth.token.CaptchaAuthenticationToken;
import com.madao.utils.JsonUtil;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义图片验证码登录
 **/
public class CaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    // 是否开启验证码功能
    //  private boolean isOpenValidateCode = true;
    private boolean postOnly = true;
    public static final String VALIDATE_CODE = "validateCode";

    public CaptchaAuthenticationFilter() {
        super(new AntPathRequestMatcher("/oauth/token", "POST"));
    }


    /**
     * 覆盖授权验证方法
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (postOnly && !HttpMethod.POST.matches(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Map<String, Object> map = JsonUtil.jsonToMap(body);
        String username = map.get("account") + "";
        String password = map.get("password") + "";
        //根据不同登录方式，生成不同类型Authentication，如这里的CaptchaAuthenticationToken
        CaptchaAuthenticationToken authRequest = new CaptchaAuthenticationToken(username, password);
        //其他参数，可以是一个字符串，也可以任意对象
        //authRequest.setDetails("其他参数");
        //将未认证Authentication交给AuthenticationManager去认证
        return getAuthenticationManager().authenticate(authRequest);

    }

}