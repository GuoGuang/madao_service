package com.youyd.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youyd.pojo.user.Resource;
import com.youyd.pojo.user.RoleResource;

import java.util.List;

/**
 * 角色_资源管理
 * @author : LGG
 * @create : 2019-06-04
 **/

public interface RoleResourceDao extends BaseMapper<RoleResource> {


	/**
	 * 根据此角色id查询匹配的资源列表
	 * @param roleId 角色id
	 * @return
	 */
	List<Resource> findResourcesOfRole(String roleId);

	void insertBatch(List<RoleResource> resources);
}
