package com.youyd.base.service;

import com.youyd.base.dao.LabelDao;
import com.youyd.base.pojo.Label;
import com.youyd.utils.CodeCommonUtil;
import com.youyd.pojo.Result;
import com.youyd.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description: 标签 业务逻辑实现类
 * @author: LGG
 * @create: 2018-09-26 15:57
 **/
@Service
public class LabelService {

	@Autowired
	private LabelDao labelDao;


	/**
	 * 按照条件查询全部标签
	 *
	 * @return
	 */
	public Result findLabelByCondition(Map map, Integer page, Integer size) {
		List<Map<Object, Object>> result = labelDao.findLabelByCondition(map);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}

	/**
	 * 根据ID查询标签
	 *
	 * @param id
	 * @return
	 */
	public Label findLabelByPrimaryKey(String id) {
		return labelDao.findLabelByPrimaryKey(id);
	}

	/**
	 * 添加标签
	 * @param label
	 */
	public void insertLabel(Label label){
		//label.setId(idWorker.nextId()+"" );//设置ID
		labelDao.insertLabel(label);
	}

	/**
	 * 修改标签
	 * @param label
	 */
	public void updateLabel(Label label){
		labelDao.updateByPrimaryKeySelective(label);
	}

	/**
	 * 删除标签
	 * @param ids
	 */
	public void deleteById(String ids){
		List<String> delIds = CodeCommonUtil.deletePart(ids);
		labelDao.deleteByPrimaryKey(delIds);
	}
}

