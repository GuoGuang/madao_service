package com.madao.api.auth;

import com.madao.constant.FeignConst;
import com.madao.fallback.auth.AuthServiceRpcFallbackFactory;
import com.madao.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 鉴权服务api
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@FeignClient(contextId = "authClient",
        value = FeignConst.SERVICE_AUTHENTICATION_AUTH,
        path = FeignConst.SERVICE_AUTHENTICATION_AUTH_PATH,
        fallbackFactory = AuthServiceRpcFallbackFactory.class)
public interface AuthServiceRpc {

    /**
     * 调用签权服务，判断用户是否有权限
     */
    @PostMapping(value = "oauth/permission")
    JsonData<Object> authPermission(@RequestParam("url") String url,
                                    @RequestParam("method") String method,
                                    @RequestHeader(HttpHeaders.AUTHORIZATION) String authentication);
}