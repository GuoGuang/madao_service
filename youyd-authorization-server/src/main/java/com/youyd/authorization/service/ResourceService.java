package com.youyd.authorization.service;

import com.youyd.pojo.user.Resource;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class ResourceService {

    //@Autowired
    //private ResourceMapper resourceMapper;

    public Set<Resource> findAll() {
	    Set<Resource> resources = new HashSet<>();
	    resources.add(new Resource());
//	    return resourceMapper.findAll();
	    return resources;
    }

    public Set<Resource> queryByRoleCodes(String[] roleCodes) {
	    HashSet<Resource> resources = new HashSet<>();
	    resources.add(new Resource());
        if (ArrayUtils.isNotEmpty(roleCodes)) {
            return resources;
        }
        return Collections.emptySet();
    }
}
