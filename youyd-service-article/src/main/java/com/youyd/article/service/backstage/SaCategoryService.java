package com.youyd.article.service.backstage;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyd.article.dao.backstage.SaCategoryDao;
import com.youyd.cache.redis.RedisService;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Category;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  文章分类
 * @author : LGG
 * @create : 2019-01-30
 **/
@Service
public class SaCategoryService {

	private final SaCategoryDao saSaCategoryDao;

	private final RedisService redisService;

	@Autowired
	public SaCategoryService(SaCategoryDao saSaCategoryDao, RedisService redisService) {
		this.saSaCategoryDao = saSaCategoryDao;
		this.redisService = redisService;
	}

	/**
	 * 查询全部列表
	 * @return IPage<Category>
	 */
	public IPage<Category> findCategoryByCondition(Category category, QueryVO queryVO ) {
		Page<Category> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());

		LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();

		if (StringUtils.isNotEmpty(category.getName())){
			queryWrapper.like(Category::getName,category.getName());
		}
		if (category.getState() != null){
			queryWrapper.eq(Category::getState,category.getState());
		}
		return saSaCategoryDao.selectPage(pr, queryWrapper);
	}

	/**
	 * 根据ID查询实体
	 * @param id 分类id
	 * @return Category
	 */
	public Category findCategoryByPrimaryKey(String categoryId) {
		return saSaCategoryDao.selectById(categoryId);
	}

	/**
	 * 增加
	 * @param category 实体
	 */
	public void insertCategory(Category category) {
		saSaCategoryDao.insert(category);
	}

	/**
	 * 修改
	 * @param category 实体
	 */
	public void updateByCategorySelective(Category category) {
		saSaCategoryDao.updateById(category);
	}

	/**
	 * 删除
	 * @param categoryIds:分类id
	 */
	public void deleteCategoryByIds(List<String> categoryIds) {
		saSaCategoryDao.deleteBatchIds(categoryIds);
	}
}
