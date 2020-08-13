package com.codeway.article.service.backstage;

import com.codeway.article.dao.backstage.CategoryDao;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.model.pojo.article.Category;
import com.codeway.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao sacategoryDao) {
        this.categoryDao = sacategoryDao;
    }

    public Page<Category> findCategoryByCondition(Category category, Pageable pageable) {
        Specification<Category> condition = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotEmpty(category.getName())) {
                predicates.add(builder.like(root.get("name"), "%" + category.getName() + "%"));
            }
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
        return categoryDao.findAll(condition, pageable);
    }

    public Category findCategoryById(String categoryId) {
        return categoryDao.findById(categoryId).orElseThrow(ResourceNotFoundException::new);
    }

    public void saveOrUpdate(Category category) {
	    if (StringUtils.isNotBlank(category.getId())){
		    Category tempCategory = categoryDao.findById(category.getId()).orElseThrow(ResourceNotFoundException::new);
		    BeanUtil.copyProperties(tempCategory, category);
	    }
        categoryDao.save(category);
    }

	public void deleteCategoryByIds(List<String> categoryIds) {
		categoryDao.deleteBatch(categoryIds);
	}
}
