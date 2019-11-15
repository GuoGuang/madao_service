package com.ibole.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ibole.pojo.user.Resource;
import com.ibole.pojo.user.RoleResource;

import java.util.List;

/**
 * 角色_资源管理
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
