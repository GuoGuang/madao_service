package com.ibole.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ibole.pojo.user.Resource;

import java.util.Set;

/**
 * @description 资源管理
 * @author LGG
 * @create 2018-09-26 16:21
 **/

public interface ResourceDao extends BaseMapper<Resource> {

	Set<Resource> findResourceByRoleIds(String[] resId);
}
