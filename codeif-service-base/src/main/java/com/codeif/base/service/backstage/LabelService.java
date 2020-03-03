package com.codeif.base.service.backstage;

import com.codeif.pojo.QueryVO;
import com.codeif.pojo.base.Label;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 标签 业务逻辑实现类
 **/
@Service
public class LabelService {

//	private final LabelDao labelDao;
//
//	@Autowired
//	public LabelService(LabelDao labelDao) {
//		this.labelDao = labelDao;
//	}


	/**
	 * 按照条件查询全部标签
	 *
	 * @return IPage<Label>
	 */
	public ArrayList<Label> findLabelByCondition(Label label, QueryVO queryVO) {
//		Page<Label> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
//		LambdaQueryWrapper<Label> queryWrapper = new LambdaQueryWrapper<>();
//		if (StringUtils.isNotEmpty(label.getLabelName())) {
//			queryWrapper.like(Label::getLabelName, label.getLabelName());
//		}
//		IPage<Label> labelIPage = labelDao.selectPage(pr, queryWrapper);
		return null;
	}

	/**
	 * 根据ID查询标签
	 * @param id 标签id
	 * @return Label
	 */
	public Label findLabelByPrimaryKey(String id) {
//		return labelDao.selectById(id);
		return null;
	}

	/**
	 * 添加标签
	 * @param label 标签实体
	 */
	public void insertLabel(Label label) {
//		labelDao.insert(label);

	}

	/**
	 * 修改标签
	 * @param label 标签实体
	 */
	public void updateLabel(Label label) {
//		labelDao.updateById(label);
	}

	/**
	 * 删除标签
	 * @param labelIds 要删除的id数组
	 */
	public void deleteById(List<String> labelIds) {
//		labelDao.deleteBatchIds(labelIds);
	}
}

