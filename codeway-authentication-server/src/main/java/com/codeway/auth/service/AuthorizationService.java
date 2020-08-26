package com.codeway.auth.service;

import com.codeway.api.user.ResourceServiceRpc;
import com.codeway.exception.custom.RemoteRpcException;
import com.codeway.model.pojo.user.Resource;
import com.codeway.utils.IdGenerate;
import com.codeway.utils.JsonData;
import com.codeway.utils.LogBack;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 鉴权服务
 **/
@Service
public class AuthorizationService {

    // 未在资源库中的URL默认标识
    private static final String NONEXISTENT_URL = "NONEXISTENT_URL";

	@Autowired
	private ResourceServiceRpc resourceServiceRpc;

    private final IdGenerate idGenerate;

    // 系统中所有权限集合
    private Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes;

    @Autowired
    public AuthorizationService(IdGenerate idGenerate) {
	    this.idGenerate = idGenerate;
    }

	@Autowired
	private HandlerMappingIntrospector mvcHandlerMappingIntrospector;

	/**
	 * 所有资源列表
	 * 一个页面的数组组装可能存在多个ajax，这里我使用逗号分隔的url字段来处理
	 */
	public Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes() {

		Set<Resource> resources = this.findResourceByCondition();

		// 处理逗号分隔的url
		Set<Resource> extendSets = new HashSet<>();
		resources.forEach(resource -> {
			if (StringUtils.isNotEmpty(resource.getUrl()) && resource.getUrl().contains(",")){
				Arrays.asList(resource.getUrl().split(",")).forEach(urlSplit -> {
					try {
						Resource resourceClone = (Resource)resource.clone();
						resourceClone.setId(String.valueOf(idGenerate.nextId()));
						resourceClone.setUrl(urlSplit);
						extendSets.add(resourceClone);
					} catch (CloneNotSupportedException e) {
						LogBack.error(e.getMessage(), e);
					}

				});
			}
		});
		resources.removeIf(resource -> StringUtils.isNotEmpty(resource.getUrl()) && resource.getUrl().contains(","));
		resources.addAll(extendSets);

		Map<RequestMatcher, ConfigAttribute> map = resources.stream().collect(Collectors.toMap(
				resource -> {
					MvcRequestMatcher mvcRequestMatcher = new MvcRequestMatcher(mvcHandlerMappingIntrospector, resource.getUrl());
					mvcRequestMatcher.setMethod(HttpMethod.resolve(resource.getMethod()));
					return mvcRequestMatcher;
				},
				resource -> new SecurityConfig(resource.getCode())
				)
		);
		return map;
	}

	/**
     * @param authRequest 访问的url,method
     * @return 有权限true, 无权限或全局资源中未找到请求url返回否
     */
    public boolean decide(HttpServletRequest authRequest) {
        LogBack.info("正在访问的url是:{}，method:{}", authRequest.getServletPath(), authRequest.getMethod());
	    resourceConfigAttributes = resourceConfigAttributes();

	    ConfigAttribute urlConfigAttribute = findConfigAttributesByUrl(authRequest);
        if (NONEXISTENT_URL.equals(urlConfigAttribute.getAttribute())){
	        LogBack.error("url未在资源池中找到，拒绝访问");
	        throw new AccessDeniedException("url未在资源池中找到，拒绝访问");
        }

	    //获取此用户所有角色拥有的权限资源
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<Resource> userResources = findResourcesByAuthorityRoles(authentication.getAuthorities());

        //用户拥有权限资源 与 url要求的资源进行对比
        return isMatch(urlConfigAttribute, userResources);
    }

    /**
     * url对应资源与用户拥有资源进行匹配
     * @param urlConfigAttribute
     * @param userResources
     */
    public boolean isMatch(ConfigAttribute urlConfigAttribute, Set<Resource> userResources) {
	    boolean isMatchBool = userResources.stream().anyMatch(
			    resource -> resource.getCode().equals(urlConfigAttribute.getAttribute()));
	    if (!isMatchBool) {
		    LogBack.error("url编码错误，请检查角色是否有此权限!");
		    throw new AccessDeniedException("url编码错误，请检查角色是否有此权限！");
	    }
	    return true;
    }

    /**
     * 根据url和method查询到对应的权限信息
     * @param authRequest request
     * @return ConfigAttribute
     */
    public ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest) {

		ConfigAttribute configAttribute = resourceConfigAttributes.keySet().stream()
				.filter(requestMatcher -> requestMatcher.matches(authRequest))
				.map(requestMatcher -> resourceConfigAttributes.get(requestMatcher))
				.peek(urlConfigAttribute -> LogBack.info("url在资源池中配置：{}", urlConfigAttribute.getAttribute()))
				.findFirst()
				.orElse(new SecurityConfig(NONEXISTENT_URL));
		return configAttribute;
	}


    /**
     * 根据用户所被授予的角色，查询到用户所拥有的资源
     * @param authorityRoles
     */
    private Set<Resource> findResourcesByAuthorityRoles(Collection<? extends GrantedAuthority> authorityRoles) {
        //用户被授予的角色
	    LogBack.info("用户的授权角色集合信息为:{}", authorityRoles);
        String[] authorityRoleIds = authorityRoles.stream()
									                .map(GrantedAuthority::getAuthority)
									                .collect(Collectors.toList())
									                .toArray(new String[authorityRoles.size()]);
        Set<Resource> resources = this.queryByRoleIds(authorityRoleIds);
	    LogBack.info("用户被授予角色的资源数量是:{}, 资源集合信息为:{}", resources.size(), resources);
        return resources;
    }

	/**
	 * 条件查询资源
	 */
	public Set<Resource> findResourceByCondition() {
		Resource resource = new Resource();
		JsonData<List<Resource>> resourceByCondition = resourceServiceRpc.findResourceByCondition(resource);
		if (!JsonData.isSuccess(resourceByCondition)){
			throw new RemoteRpcException(resourceByCondition);
		}
		List<Resource> resources = resourceByCondition.getData();
		return new HashSet<>(resources);
	}

	/**
	 * 根据角色id查询资源
	 * @param roleIds 角色id集合
	 * @return Set<Resource>
	 */
	public Set<Resource> queryByRoleIds(String[] roleIds) {
		JsonData<List<Resource>> resourceOfRole = resourceServiceRpc.findResourceByRoleIds(roleIds);
		if (!JsonData.isSuccess(resourceOfRole)){
			throw new RemoteRpcException(resourceOfRole);
		}
		List<Resource> data = resourceOfRole.getData();
		Set<Resource> resourcesSet = new HashSet<>(data);
		Optional<Set<Resource>> resourcesSetOpt = Optional.of(resourcesSet);
		return resourcesSetOpt.orElseGet(Collections::emptySet);
	}
}
