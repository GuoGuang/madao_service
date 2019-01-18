package com.youyd.article.service.impl;


import com.youyd.article.dao.CategoryDao;
import com.youyd.article.pojo.Category;
import com.youyd.article.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  文章分类
 */

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;


	/**
	 * 查询全部列表
	 * @return
	 */
	@Override
	public List findCategoryByCondition() {
		return categoryDao.selectList(null);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	@Override
	public Category findCategoryByPrimaryKey(String id) {
		return categoryDao.selectById(id);
	}

	/**
	 * 增加
	 * @param category
	 */
	@Override
	public void insertCategory(Category category) {
		categoryDao.insert(category);
	}

	/**
	 * 修改
	 * @param category
	 */
	@Override
	public void updateByCategorySelective(Category category) {
		categoryDao.updateById(category);
	}

	/**
	 * 删除
	 * @param categoryIds:分类id
	 */
	@Override
	public void deleteByPrimaryKey(List categoryIds) {
		categoryDao.deleteBatchIds(categoryIds);
	}
}
