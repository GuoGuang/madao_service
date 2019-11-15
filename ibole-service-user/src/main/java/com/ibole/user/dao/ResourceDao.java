package com.ibole.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ibole.pojo.user.Resource;

import java.util.Set;

/**
 * 资源管理
 **/

public interface ResourceDao extends BaseMapper<Resource> {

	Set<Resource> findResourceByRoleIds(String[] resId);
}
