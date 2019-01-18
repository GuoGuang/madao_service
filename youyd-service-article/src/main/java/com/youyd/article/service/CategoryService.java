package com.youyd.article.service;

import com.youyd.article.pojo.Category;

import java.util.List;

public interface CategoryService {
	List findCategoryByCondition();

	Category findCategoryByPrimaryKey(String id);

	void insertCategory(Category category);

	void updateByCategorySelective(Category category);

	void deleteByPrimaryKey(List categoryIds);
}
