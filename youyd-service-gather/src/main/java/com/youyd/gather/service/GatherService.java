package com.youyd.gather.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.gather.dao.GatherDao;
import com.youyd.gather.pojo.Gather;
import com.youyd.pojo.QueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @description: 问答板块:问题服务
 * @author: LGG
 * @create: 2019-03-07
 **/
@Service
public class GatherService {

	@Autowired
	private GatherDao gatherDao;


	/**
	 * 查询全部列表
	 * @return
	 */
	public IPage<Gather> findGatherByCondition(QueryVO queryVO){
		Page<Gather> pr = new Page<>(queryVO.getPageSize(),queryVO.getPageSize());
		QueryWrapper<Gather> queryWrapper = new QueryWrapper<>();
		return gatherDao.selectPage(pr, queryWrapper);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Gather findGatherByPrimaryKey(String id) {
		return gatherDao.selectById(id);
	}

	/**
	 * 增加
	 * @param gathering
	 */
	public void insertGather(Gather gathering) {
		gatherDao.insert(gathering);
	}

	/**
	 * 修改
	 * @param gathering
	 */
	public void updateByPrimaryKeySelective(Gather gathering) {
		gatherDao.updateById(gathering);
	}

	/**
	 * 删除
	 * @param id
	 */
	public boolean deleteByPrimaryKey(List ids) {
		int i = gatherDao.deleteBatchIds(ids);
		return SqlHelper.retBool(i);
	}


}
