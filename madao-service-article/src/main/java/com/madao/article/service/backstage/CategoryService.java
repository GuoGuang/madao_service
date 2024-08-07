package com.madao.article.service.backstage;

import com.madao.article.mapper.CategoryMapper;
import com.madao.article.repository.backstage.ArticleRepository;
import com.madao.article.repository.backstage.CategoryRepository;
import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.model.dto.article.CategoryDto;
import com.madao.model.entity.article.Article;
import com.madao.model.entity.article.Category;
import com.madao.utils.BeanUtil;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Service
@AllArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final ArticleRepository articleRepository;
	private final CategoryMapper categoryMapper;

	public Page<CategoryDto> findCategoryByCondition(CategoryDto categoryDto, Pageable pageable) {
		Specification<Category> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(categoryDto.getName())) {
				predicates.add(builder.like(root.get("name"), "%" + categoryDto.getName() + "%"));
			}
			return query.where(predicates.toArray(new Predicate[0])).getRestriction();
		};
		Page<CategoryDto> categoryDtoPage = categoryRepository.findAll(condition, pageable).map(categoryMapper::toDto);

		List<String> ids = categoryDtoPage.getContent().stream()
				.map(CategoryDto::getId)
				.toList();
		Map<String, List<Article>> articleCollect = articleRepository.findByCategoryIdIn(ids)
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
		return categoryRepository.findById(categoryId)
				.map(categoryMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	public void saveOrUpdate(CategoryDto categoryDto) {
		if (StringUtils.isNotBlank(categoryDto.getId())) {
			Category tempCategory = categoryRepository.findById(categoryDto.getId())
					.orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(tempCategory, categoryDto);
		}
		categoryRepository.save(categoryMapper.toEntity(categoryDto));
	}

	public void deleteCategoryByIds(List<String> categoryIds) {
		categoryRepository.deleteBatch(categoryIds);
	}
}
