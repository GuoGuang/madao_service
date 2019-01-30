package com.youyd.article.service;


import com.youyd.article.dao.CategoryDao;
import com.youyd.article.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 文章分类
 * @author: LGG
 * @create: 2019-01-30
 **/
@Service
public class CategoryService{

	@Autowired
	private CategoryDao categoryDao;


	/**
	 * 查询全部列表
	 * @return
	 */
	public List findCategoryByCondition() {
		return categoryDao.selectList(null);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Category findCategoryByPrimaryKey(String id) {
		return categoryDao.selectById(id);
	}

	/**
	 * 增加
	 * @param category
	 */
	public void insertCategory(Category category) {
		categoryDao.insert(category);
	}

	/**
	 * 修改
	 * @param category
	 */
	public void updateByCategorySelective(Category category) {
		categoryDao.updateById(category);
	}

	/**
	 * 删除
	 * @param categoryIds:分类id
	 */
	public void deleteByPrimaryKey(List categoryIds) {
		categoryDao.deleteBatchIds(categoryIds);
	}
}
