package com.youyd.user.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.user.Resource;
import com.youyd.user.dao.ResourceDao;
import com.youyd.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 资源接口实现
 * @author : LGG
 * @create : 2018-09-27
 **/
@Service
public class ResourceService{

	private final ResourceDao resourceDao;

	@Autowired
	public ResourceService(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	/**
	 * 条件查询资源
	 * @param resource 资源实体
	 * @param queryVO 查询参数
	 * @return List
	 */
	public List<Resource> findResourceByCondition(Resource resource, QueryVO queryVO) {
		LambdaQueryWrapper<Resource> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(resource.getName())){
			queryWrapper.eq(Resource::getName,resource.getName());
		}
		queryWrapper.orderByAsc(Resource::getSort);
		List<Resource> resources = resourceDao.selectList(queryWrapper);
		return resources;
	}

	public Resource findResourceById(String resId) {
		return resourceDao.selectById(resId);
	}

	public Set<Resource> findResourceByRoleIds(String[] resId) {
		return resourceDao.findResourceByRoleIds(resId);
	}

	public boolean updateByPrimaryKey(Resource resources) {
		int i = resourceDao.updateById(resources);
		return SqlHelper.retBool(i);
	}

	public boolean insertSelective(Resource resource) {
		resource.setCreateAt(DateUtil.getTimestamp());
		resource.setUpdateAt(DateUtil.getTimestamp());
		int insert = resourceDao.insert(resource);
		return SqlHelper.retBool(insert);
	}

	public boolean deleteByIds(List<String> resId) {
		int i = resourceDao.deleteBatchIds(resId);
		return SqlHelper.retBool(i);
	}
}
