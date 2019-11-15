package com.ibole.fallback.user;

import com.ibole.api.user.ResourceServiceRpc;
import com.ibole.enums.StatusEnum;
import com.ibole.pojo.user.Resource;
import com.ibole.utils.JsonData;
import com.ibole.utils.LogBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 接口调用失败处理
 **/

@Component
public class ResourceServiceRpcFallbackFactory implements FallbackFactory<ResourceServiceRpc> {

	private static final String ERROR_INFO = "ResourceServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

	@Override
	public ResourceServiceRpc create(Throwable throwable) {
		return new ResourceServiceRpc() {

			@Override
			public JsonData findResourceByCondition(Resource resource) {
				LogBack.error(ERROR_INFO,"findResourceByCondition",resource,throwable);
				return new JsonData(false, StatusEnum.RPC_ERROR.getCode(), StatusEnum.RPC_ERROR.getMsg());

			}

			@Override
			public JsonData<List<Resource>> findResourceByRoleIds(String[] roleIds) {
				LogBack.error(ERROR_INFO,"findResourceByCondition",roleIds,throwable);
				return new JsonData(false, StatusEnum.RPC_ERROR.getCode(), StatusEnum.RPC_ERROR.getMsg());

			}


		};
	}
}
