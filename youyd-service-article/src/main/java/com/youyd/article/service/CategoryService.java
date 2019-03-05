package com.youyd.article.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyd.article.dao.CategoryDao;
import com.youyd.article.pojo.Article;
import com.youyd.article.pojo.Category;
import com.youyd.cache.constant.RedisConstant;
import com.youyd.cache.redis.RedisService;
import com.youyd.pojo.QueryVO;
import com.youyd.utils.JsonUtil;
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

	@Autowired
	private RedisService redisService;

	/**
	 * 查询全部列表
	 * @return
	 */
	public IPage<Category> findCategoryByCondition(Category category, QueryVO queryVO) {
		Page<Category> pr = new Page<>(queryVO.getPage(),queryVO.getLimit());
		QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
		return categoryDao.selectPage(pr, queryWrapper);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Category findCategoryByPrimaryKey(String id) {
		Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + id);
		Category category = JsonUtil.mapToPojo(mapJson, Category.class);
		// 如果缓存没有则到数据库查询并放入缓存,有效期一天
		if(category==null) {
			category = categoryDao.selectById(id);
			redisService.set(RedisConstant.REDIS_KEY_ARTICLE+ id, category,RedisConstant.REDIS_TIME_DAY);
		}
		return category;
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
