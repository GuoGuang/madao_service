package com.youyd.authorization.service;

import com.youyd.pojo.user.Resource;
import com.youyd.utils.LogBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    /**
     * 未在资源库中的URL默认标识
     */
    public static final String NONEXISTENT_URL = "NONEXISTENT_URL";

    @Autowired
    private ResourceService resourceService;

    /**
     * 系统中所有权限集合
     */
    Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes;

    /**
     * 从数据库中加载注入
     *
     * @param resourceConfigAttributes
     */
    @Autowired
    public AuthenticationService(Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes) {
        this.resourceConfigAttributes = resourceConfigAttributes;
    }

    /**
     * @param authRequest 访问的url,method
     * @return 有权限true, 无权限或全局资源中未找到请求url返回否
     */
    public boolean decide(HttpServletRequest authRequest) {
        LogBack.info("正在访问的url是:{}，method:{}", authRequest.getServletPath(), authRequest.getMethod());
	    //获取用户认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取此url，method访问对应的权限资源信息
        ConfigAttribute urlConfigAttribute = findConfigAttributesByUrl(authRequest);
        if (NONEXISTENT_URL.equals(urlConfigAttribute.getAttribute())){
	        LogBack.error("url未在资源池中找到，拒绝访问");
	        throw new AccessDeniedException("url未在资源池中找到，拒绝访问");
        }
	    //获取此访问用户所有角色拥有的权限资源
        Set<Resource> userResources = findResourcesByAuthorityRoles(authentication.getAuthorities());
        //用户拥有权限资源 与 url要求的资源进行对比
        return isMatch(urlConfigAttribute, userResources);
    }

    /**
     * url对应资源与用户拥有资源进行匹配
     *
     * @param urlConfigAttribute
     * @param userResources
     * @return
     */
    public boolean isMatch(ConfigAttribute urlConfigAttribute, Set<Resource> userResources) {
        return userResources.stream().anyMatch(resource -> resource.getCode().equals(urlConfigAttribute.getAttribute()));
    }

    /**
     * 根据url和method查询到对应的权限信息
     *
     * @param authRequest
     * @return
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
     * @return
     */
    private Set<Resource> findResourcesByAuthorityRoles(Collection<? extends GrantedAuthority> authorityRoles) {
        //用户被授予的角色
	    LogBack.info("用户的授权角色集合信息为:{}", authorityRoles);
        String[] authorityRoleCodes = authorityRoles.stream()
									                .map(GrantedAuthority::getAuthority)
									                .collect(Collectors.toList())
									                .toArray(new String[authorityRoles.size()]);
        Set<Resource> resources = resourceService.queryByRoleCodes(authorityRoleCodes);
	    LogBack.info("用户被授予角色的资源数量是:{}, 资源集合信息为:{}", resources.size(), resources);
        return resources;
    }
}
