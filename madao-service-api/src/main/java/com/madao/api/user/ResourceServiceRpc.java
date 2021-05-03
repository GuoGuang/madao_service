package com.madao.api.user;

import com.madao.FeignClientConfig;
import com.madao.constant.FeignConst;
import com.madao.fallback.user.ResourceServiceRpcFallbackFactory;
import com.madao.model.pojo.user.Resource;
import com.madao.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 资源服务api
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@FeignClient(contextId = "resourceClient",
        value = FeignConst.SERVICE_USER,
        path = FeignConst.SERVICE_RESOURCE_PATH,
        fallbackFactory = ResourceServiceRpcFallbackFactory.class,
        configuration = {FeignClientConfig.class})
public interface ResourceServiceRpc {

    /**
     * 条件查询资源
     *
     * @param resource
     * @return
     */
    @GetMapping
    JsonData<List<Resource>> findResourceByCondition();

    @GetMapping("/roles")
    JsonData<List<Resource>> findResourceByRoleIds(@RequestParam("roleId") String[] roleIds);
}
