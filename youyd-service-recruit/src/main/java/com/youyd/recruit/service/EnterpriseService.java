package com.youyd.recruit.service;

import com.youyd.pojo.Result;
import com.youyd.utils.CodeCommonUtil;
import com.youyd.utils.StatusCode;
import com.youyd.recruit.dao.EnterpriseDao;
import com.youyd.recruit.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description: 企业服务
 * @author: LGG
 * @create: 2018-09-27
 **/
@Service
public class EnterpriseService {

	@Autowired
	private EnterpriseDao enterpriseDao;


	/**
	 * 按照条件查询
	 * @param map
	 * @param page
	 * @param size
	 * @return
	 */
	public Result findEnterpriseByCondition(Map map, Integer page, Integer size) {
		List<Map<Object, Object>> result = enterpriseDao.findEnterpriseByCondition(map);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}

	/**
	 * 根据ID查询企业
	 * @param id
	 * @return
	 */
	public Enterprise findEnterpriseByPrimaryKey(String id) {
		return enterpriseDao.findEnterpriseByPrimaryKey(id);
	}

	/**
	 * 增加
	 * @param enterprise
	 */
	public void insertEnterprise(Enterprise enterprise) {
		enterpriseDao.insertEnterprise(enterprise);
	}

	/**
	 * 修改
	 * @param enterprise
	 */
	public void updateEnterprise(Enterprise enterprise) {
		enterpriseDao.updateByPrimaryKeySelective(enterprise);
	}

	/**
	 * 删除
	 * @param ids
	 */
	public void deleteById(String ids) {
		List<String> delIds = CodeCommonUtil.deletePart(ids);
		enterpriseDao.deleteByPrimaryKey(delIds);
	}
}
