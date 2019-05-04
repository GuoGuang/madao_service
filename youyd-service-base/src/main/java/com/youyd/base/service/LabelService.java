package com.youyd.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyd.base.dao.LabelDao;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.base.Label;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 标签 业务逻辑实现类
 * @author: LGG
 * @create: 2018-09-26 15:57
 **/
@Service
public class LabelService {

	private final LabelDao labelDao;

	@Autowired
	public LabelService(LabelDao labelDao) {
		this.labelDao = labelDao;
	}


	/**
	 * 按照条件查询全部标签
	 * @return IPage<Label>
	 */
	public IPage<Label> findLabelByCondition(Label label, QueryVO queryVO) {
		Page<Label> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		LambdaQueryWrapper<Label> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(label.getLabelName())) {
			queryWrapper.like(Label::getLabelName, label.getLabelName());
		}
		IPage<Label> labelIPage = labelDao.selectPage(pr, queryWrapper);
		return labelIPage;
	}

	/**
	 * 根据ID查询标签
	 * @param id 标签id
	 * @return Label
	 */
	public Label findLabelByPrimaryKey(String id) {
		return labelDao.selectById(id);
	}

	/**
	 * 添加标签
	 * @param label 标签实体
	 */
	public void insertLabel(Label label){
		labelDao.insert(label);
	}

	/**
	 * 修改标签
	 * @param label 标签实体
	 */
	public void updateLabel(Label label){
		labelDao.updateById(label);
	}

	/**
	 * 删除标签
	 * @param labelIds 要删除的id数组
	 */
	public void deleteById(List<String> labelIds){
		labelDao.deleteBatchIds(labelIds);
	}
}

