package com.youyd.auth.service;

import com.youyd.api.base.LoginLogServiceRpc;
import com.youyd.cache.redis.RedisService;
import com.youyd.constant.FeignConst;
import com.youyd.pojo.base.LoginLog;
import com.youyd.pojo.user.AuthToken;
import com.youyd.utils.DateUtil;
import com.youyd.utils.HttpServletUtil;
import com.youyd.utils.JsonUtil;
import com.youyd.utils.security.JWTAuthentication;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * 登录，认证
 * @author : LGG
 * @create : 2019-06-18 14:34
 **/
@Service
public class AuthService {

    @Value("${auth.tokenValiditySeconds}")
    int tokenValiditySeconds;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RedisService redisService;

	@Autowired
	LoginLogServiceRpc loginLogServiceRpc;

    @Autowired
    RestTemplate restTemplate;

    //用户认证申请令牌，将令牌存储到redis
    public AuthToken login(String username, String password, String clientId, String clientSecret, HttpServletRequest request) throws Exception {

        //请求spring security申请令牌
        AuthToken authToken = applyToken(username, password, clientId, clientSecret,request);
        //用户身份令牌
        String accessToken = authToken.getAccess_token();
        //存储到redis中的内容
        String jsonString = JsonUtil.toJsonString(authToken);
        //将令牌存储到redis
        boolean result = saveToken(accessToken, jsonString, tokenValiditySeconds);
        if (!result) {
//            ExceptionCast.cast(AuthCode.AUTH_LOGIN_TOKEN_SAVEFAIL);
	        // TODO 异常
	        throw new RuntimeException();
        }
        return authToken;

    }
    //存储到令牌到redis

    /**
     *
     * @param access_token 用户身份令牌
     * @param content  内容就是AuthToken对象的内容
     * @param ttl 过期时间
     * @return
     */
    private boolean saveToken(String access_token,String content,long ttl){
        String key = "user_token:" + access_token;
        redisService.setKeyStr(key,content,ttl);
	    Long expire = redisService.getExpire(key);
        return expire>0;
    }
    //申请令牌
    private AuthToken applyToken(String username, String password, String clientId, String clientSecret, HttpServletRequest request)throws Exception{
        //从eureka中获取认证服务的地址（因为spring security在认证服务中）
        //从eureka中获取认证服务的一个实例的地址
        ServiceInstance serviceInstance = loadBalancerClient.choose(FeignConst.SERVICE_AUTHENTICATION_AUTH);
        //此地址就是http://ip:port
        URI uri = serviceInstance.getUri();
        //令牌申请的地址 http://localhost:40400/auth/oauth/token
        String authUrl = uri+ "/oauth/token";
        //定义header
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        String httpBasic = getHttpBasic(clientId, clientSecret);
        header.add(HttpHeaders.AUTHORIZATION,httpBasic);

        //定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","password");
        body.add("username",username);
        body.add("password",password);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        //String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables

        //设置restTemplate远程调用时候，对400和401不让报错，正确返回数据
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if(response.getRawStatusCode()!=400 && response.getRawStatusCode()!=401){
                    super.handleError(response);
                }
            }
        });

        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);

        //申请令牌信息
        Map bodyMap = exchange.getBody();

        if(bodyMap == null || bodyMap.get("status") != null ){
            throw new InvalidGrantException("");
        }
        AuthToken authToken = new AuthToken();
        authToken.setAccess_token((String) bodyMap.get("jti"));//用户身份令牌
        authToken.setRefresh_token((String) bodyMap.get("refresh_token"));//刷新令牌
        authToken.setJwt_token((String) bodyMap.get("access_token"));//jwt令牌

	    String auth = (String) bodyMap.get("access_token");
	    Map<String, String> stringStringMap = JWTAuthentication.parseJwtToClaims(auth);
	    insertLoginLog(auth,stringStringMap.get("id"),request);
        return authToken;
    }



    //获取httpbasic的串
    private String getHttpBasic(String clientId,String clientSecret){
        String string = clientId+":"+clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic "+new String(encode);
    }

	public void logout(String token) {
		String key = "user_token:" + JWTAuthentication.getFullAuthorization(token);
		redisService.del(key);
	}

	/**
	 * 添加登录日志
	 * @param auth 令牌
	 * @param userId 用户id
	 * @param request request
	 */
	private void insertLoginLog(String auth, String userId, HttpServletRequest request) {
		// 添加登录日志
		LoginLog loginLog = new LoginLog();
		loginLog.setClientIp(HttpServletUtil.getIpAddr(request));
		loginLog.setUserId(userId);
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		loginLog.setBrowser(userAgent.getBrowser().getName());
		loginLog.setOsInfo(userAgent.getOperatingSystem().getName());
		loginLog.setCreateAt(DateUtil.getTimestamp());
		loginLog.setUpdateAt(DateUtil.getTimestamp());
		loginLogServiceRpc.insertLoginLog("Bearer "+auth,loginLog);

	}
}
