package com.ibole.article.service.backstage;


import com.ibole.article.dao.backstage.CategoryDao;
import com.ibole.db.redis.service.RedisService;
import com.ibole.exception.custom.ResourceNotFoundException;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Category;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 *  文章分类
 **/
@Service
public class CategoryService {

	private final CategoryDao categoryDao;

	private final RedisService redisService;

	@Autowired
	public CategoryService(CategoryDao sacategoryDao, RedisService redisService) {
		this.categoryDao = sacategoryDao;
		this.redisService = redisService;
	}

	/**
	 * 查询全部列表
	 *
	 * @return IPage<Category>
	 */
	public Page<Category> findCategoryByCondition(Category category, QueryVO queryVO) {
		Specification<Category> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(category.getName())) {
				predicates.add(builder.like(root.get("name"), "%" + category.getName() + "%"));
			}
			if (category.getState() != null) {
				predicates.add(builder.equal(root.get("state"), category.getState()));
			}
			Predicate[] ps = new Predicate[predicates.size()];
			query.where(builder.and(predicates.toArray(ps)));
			query.orderBy(builder.desc(root.get("createAt").as(Long.class)));
			return null;
		};
		return categoryDao.findAll(condition, queryVO.getPageable());

	}

	/**
	 * 根据ID查询实体
	 *
	 * @param categoryId 分类id
	 * @return Category
	 */
	public Category findCategoryById(String categoryId) {
		return categoryDao.findById(categoryId).orElseThrow(ResourceNotFoundException::new);
	}

	public void saveOrUpdate(Category category) {
		categoryDao.save(category);
	}

	/**
	 * 删除
	 *
	 * @param categoryIds:分类id
	 */
	public void deleteCategoryByIds(List<String> categoryIds) {
		categoryDao.deleteBatch(categoryIds);
	}
}
