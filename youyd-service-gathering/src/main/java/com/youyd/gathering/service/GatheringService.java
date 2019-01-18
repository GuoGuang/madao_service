package com.youyd.gathering.service;


import com.youyd.gathering.dao.GatheringDao;
import com.youyd.gathering.pojo.Gathering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  问答板块:问题服务
 */

@Service
public class GatheringService {

	@Autowired
	private GatheringDao gatheringDao;


	/**
	 * 查询全部列表
	 * @return
	 */
	public List findGatheringByCondition() {
		return gatheringDao.findGatheringByCondition(null);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Gathering findGatheringByPrimaryKey(String id) {
		return gatheringDao.findGatheringByPrimaryKey(id);
	}

	/**
	 * 增加
	 * @param gathering
	 */
	public void insertGathering(Gathering gathering) {
		gatheringDao.insertGathering(gathering);
	}

	/**
	 * 修改
	 * @param gathering
	 */
	public void updateByPrimaryKeySelective(Gathering gathering) {
		gatheringDao.updateByPrimaryKeySelective(gathering);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteByPrimaryKey(String id) {
		gatheringDao.deleteByPrimaryKey(null);
	}


}
