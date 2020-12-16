package com.madaoo.fallback.user;

import com.madaoo.api.user.BlogUserServiceRpc;
import com.madaoo.enums.StatusEnum;
import com.madaoo.utils.JsonData;
import com.madaoo.utils.LogBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 接口调用失败处理
 **/

@Component
public class BlogUserServiceRpcFallbackFactory implements FallbackFactory<BlogUserServiceRpc> {

	private static final String ERROR_INFO = "接口UserServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

	@Override
	public BlogUserServiceRpc create(Throwable throwable) {
		return new BlogUserServiceRpc() {
			@Override
			public JsonData loginWithGithub(Map<String, Object> userInfo) {
				LogBack.error(ERROR_INFO, "registerUserWithGithub", userInfo, throwable);
				return JsonData.failed(StatusEnum.RPC_ERROR);
			}
		};
	}
}
