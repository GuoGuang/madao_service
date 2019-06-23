package com.youyd.authorization.config;

import com.youyd.authorization.service.ResourceService;
import com.youyd.pojo.user.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class LoadResourceDefine {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    @Bean
    public Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes() {
        Set<Resource> resources = resourceService.findAll();
        Map<RequestMatcher, ConfigAttribute> map = resources.stream()
                .collect(Collectors.toMap(
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
}
