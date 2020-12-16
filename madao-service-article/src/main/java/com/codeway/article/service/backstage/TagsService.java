package com.madaoo.article.service.backstage;

import com.madaoo.article.dao.backstage.ArticleTagDao;
import com.madaoo.article.dao.backstage.TagDao;
import com.madaoo.article.mapper.TagMapper;
import com.madaoo.exception.custom.ResourceNotFoundException;
import com.madaoo.model.dto.article.TagDto;
import com.madaoo.model.pojo.article.ArticleTag;
import com.madaoo.model.pojo.article.Tag;
import com.madaoo.utils.BeanUtil;
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
public class TagsService {

	private final TagDao tagDao;
	private final ArticleTagDao articleTagDao;
	private final TagMapper tagMapper;

	public TagsService(TagDao tagDao, ArticleTagDao articleTagDao, TagMapper tagMapper) {
		this.tagDao = tagDao;
		this.articleTagDao = articleTagDao;
		this.tagMapper = tagMapper;
	}

	public Page<TagDto> findTagsByCondition(TagDto tagDto, Pageable pageable) {
		Specification<Tag> condition = (root, query, builder) -> {
			List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(tagDto.getName())) {
				predicates.add(builder.like(root.get("name"), "%" + tagDto.getName() + "%"));
			}
			if (tagDto.getState() != null) {
				predicates.add(builder.equal(root.get("state"), tagDto.getState()));
			}
			return query.where(predicates.toArray(new Predicate[0])).getRestriction();
		};
		Page<TagDto> tagsQueryResults = tagDao.findAll(condition, pageable)
				.map(tagMapper::toDto);

		List<ArticleTag> articleTags = articleTagDao.findAllByTagIdIn(tagsQueryResults.getContent().stream().map(TagDto::getId).collect(Collectors.toList()));

		Map<String, List<ArticleTag>> tagIds = articleTags.stream()
				.collect(Collectors.groupingBy(ArticleTag::getTagId));

		tagsQueryResults.forEach(tag -> {
			if (tagIds.get(tag.getId()) != null) {
				tag.setTagsCount(tagIds.get(tag.getId()).size());
			}
		});
		return tagsQueryResults;
	}

	public TagDto findTagsById(String id) {
		return tagDao.findById(id).map(tagMapper::toDto).orElseThrow(ResourceNotFoundException::new);
	}

	public void saveOrUpdate(TagDto tagDto) {
		if (StringUtils.isNotBlank(tagDto.getId())) {
			Tag tempTags = tagDao.findById(tagDto.getId()).orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(tempTags, tagDto);
		}
		tagDao.save(tagMapper.toEntity(tagDto));
	}

	public void deleteBatch(List<String> tagIds) {
		tagDao.deleteBatch(tagIds);
		articleTagDao.deleteByTagIdIn(tagIds);
	}
}
