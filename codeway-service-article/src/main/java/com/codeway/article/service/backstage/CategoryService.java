package com.codeway.article.service.backstage;

import com.codeway.article.dao.backstage.ArticleDao;
import com.codeway.article.dao.backstage.CategoryDao;
import com.codeway.article.mapper.CategoryMapper;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.model.dto.article.CategoryDto;
import com.codeway.model.pojo.article.Article;
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
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryService {

	private final CategoryDao categoryDao;
	private final ArticleDao articleDao;
	private final CategoryMapper categoryMapper;

	public CategoryService(CategoryDao categoryDao, ArticleDao articleDao, CategoryMapper categoryMapper) {
		this.categoryDao = categoryDao;
		this.articleDao = articleDao;
		this.categoryMapper = categoryMapper;
	}

	public Page<CategoryDto> findCategoryByCondition(CategoryDto categoryDto, Pageable pageable) {
		Specification<Category> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(categoryDto.getName())) {
				predicates.add(builder.like(root.get("name"), "%" + categoryDto.getName() + "%"));
			}
			return query.where(predicates.toArray(new Predicate[0])).getRestriction();
		};
		Page<CategoryDto> categoryDtoPage = categoryDao.findAll(condition, pageable).map(categoryMapper::toDto);

		List<String> ids = categoryDtoPage.getContent().stream()
				.map(CategoryDto::getId)
				.collect(Collectors.toList());
		Map<String, List<Article>> articleCollect = articleDao.findByCategoryIdIn(ids)
				.stream()
				.collect(Collectors.groupingBy(Article::getCategoryId));

		categoryDtoPage.forEach(articleInfo -> {
			if (articleCollect.get(articleInfo.getId()) != null) {
				articleInfo.setArticleCount(articleCollect.get(articleInfo.getId()).size());
			}
		});

		return categoryDtoPage;
	}

	public CategoryDto findCategoryById(String categoryId) {
		return categoryDao.findById(categoryId)
				.map(categoryMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	public void saveOrUpdate(CategoryDto categoryDto) {
		if (StringUtils.isNotBlank(categoryDto.getId())) {
			Category tempCategory = categoryDao.findById(categoryDto.getId())
					.orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(tempCategory, categoryDto);
		}
		categoryDao.save(categoryMapper.toEntity(categoryDto));
	}

	public void deleteCategoryByIds(List<String> categoryIds) {
		categoryDao.deleteBatch(categoryIds);
	}
}
