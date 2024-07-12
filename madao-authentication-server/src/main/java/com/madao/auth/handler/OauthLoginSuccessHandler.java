package com.madao.auth.handler;

import com.madao.utils.RedisUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 三方登录（Github、QQ、Wechat）成功处理器
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Component
@AllArgsConstructor
public class OauthLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final RedisUtil redisUtil;
    private final OAuth2ClientProperties oAuth2ClientProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("三方登录成功！");
        OAuth2ClientProperties.Registration github = oAuth2ClientProperties.getRegistration().get("github");
//        saveToken(jti.toString(), jsonString, CommonConst.TIME_OUT_DAY);
    }

    private boolean saveToken(String accessToken, String content, long ttl) {
        String key = "user_token:" + accessToken;
        redisUtil.setKeyStr(key, content, ttl);
        Long expire = redisUtil.getExpire(key);
        return expire > 0;
    }

}

