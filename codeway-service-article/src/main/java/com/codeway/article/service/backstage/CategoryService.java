package com.codeway.article.service.backstage;


import com.codeway.article.dao.backstage.CategoryDao;
import com.codeway.db.redis.service.RedisService;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.pojo.QueryVO;
import com.codeway.pojo.article.Category;
import com.codeway.pojo.article.QCategory;
import com.codeway.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章分类
 **/
@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    private final RedisService redisService;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    public CategoryService(CategoryDao sacategoryDao, RedisService redisService) {
        this.categoryDao = sacategoryDao;
        this.redisService = redisService;
    }

    public QueryResults<Category> findCategoryByCondition(Category category, QueryVO queryVO) {

        QCategory qCategory = QCategory.category;
        Predicate predicate = null;
        OrderSpecifier<?> sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qCategory);
        if (StringUtils.isNotEmpty(category.getName())) {
            predicate = ExpressionUtils.and(predicate, qCategory.name.like(category.getName()));
        }
        if (category.getState() != null) {
            predicate = ExpressionUtils.and(predicate, qCategory.state.eq(category.getState()));
        }
        if (StringUtils.isNotEmpty(queryVO.getFieldSort())) {
            sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qCategory, queryVO.getFieldSort());
        }
        QueryResults<Category> queryResults = jpaQueryFactory
                .selectFrom(qCategory)
                .where(predicate)
                .offset(queryVO.getPageNum())
                .limit(queryVO.getPageSize())
                .orderBy(sortedColumn)
                .fetchResults();
        return queryResults;

    }

    public Category findCategoryById(String categoryId) {
        return categoryDao.findById(categoryId).orElseThrow(ResourceNotFoundException::new);
    }

    public void saveOrUpdate(Category category) {
        categoryDao.save(category);
    }

	public void deleteCategoryByIds(List<String> categoryIds) {
		categoryDao.deleteBatch(categoryIds);
	}
}
