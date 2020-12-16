package com.madaoo.auth.handler;

import com.google.common.collect.Maps;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;


/**
 * 自定义token携带内容  {access_token:"xxx",organization:"yyy"}
 **/
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = Maps.newHashMap();
        //自定义token内容，加入组织机构信息
        additionalInfo.put("organization", authentication.getName());
	    DefaultOAuth2AccessToken auth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
        auth2AccessToken.setAdditionalInformation(additionalInfo);
	    return accessToken;
    }
}