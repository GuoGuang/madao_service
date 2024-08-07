package com.madao.article.service.backstage;

import com.madao.article.mapper.TagMapper;
import com.madao.article.repository.backstage.ArticleTagRepository;
import com.madao.article.repository.backstage.TagRepository;
import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.model.dto.article.TagDto;
import com.madao.model.entity.article.ArticleTag;
import com.madao.model.entity.article.Tag;
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
public class TagsService {

	private final TagRepository tagRepository;
	private final ArticleTagRepository articleTagRepository;
	private final TagMapper tagMapper;

	public Page<TagDto> findTagsByCondition(TagDto tagDto, Pageable pageable) {
		Specification<Tag> condition = (root, query, builder) -> {
			List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(tagDto.getName())) {
				predicates.add(builder.like(root.get("name"), "%" + tagDto.getName() + "%"));
			}
			if (tagDto.getState() != null) {
				predicates.add(builder.equal(root.get("state"), tagDto.getState()));
			}
			return query.where(predicates.toArray(new Predicate[0])).getRestriction();
		};
		Page<TagDto> tagsQueryResults = tagRepository.findAll(condition, pageable)
				.map(tagMapper::toDto);

		List<ArticleTag> articleTags = articleTagRepository.findAllByTagIdIn(tagsQueryResults.getContent().stream().map(TagDto::getId).toList());

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
		return tagRepository.findById(id).map(tagMapper::toDto).orElseThrow(ResourceNotFoundException::new);
	}

	public void saveOrUpdate(TagDto tagDto) {
		if (StringUtils.isNotBlank(tagDto.getId())) {
			Tag tempTags = tagRepository.findById(tagDto.getId()).orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(tempTags, tagDto);
		}
		tagRepository.save(tagMapper.toEntity(tagDto));
	}

	public void deleteBatch(List<String> tagIds) {
		tagRepository.deleteBatch(tagIds);
		articleTagRepository.deleteByTagIdIn(tagIds);
	}
}
