package com.codeway.article.service.backstage;

import com.codeway.article.dao.backstage.TagsDao;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.pojo.article.Tags;
import com.codeway.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagsService {

	private final TagsDao tagsDao;

	@Autowired
	public TagsService(TagsDao tagsDao) {
		this.tagsDao = tagsDao;
	}

	public Page<Tags> findTagsByCondition(Tags tags, Pageable pageable) {
		Specification<Tags> condition = (root, query, builder) -> {
			List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(tags.getName())) {
				predicates.add(builder.like(root.get("name"), "%" + tags.getName() + "%"));
			}
			if (tags.getState() != null) {
				predicates.add(builder.equal(root.get("state"), tags.getState()));
			}
			return query.where(predicates.toArray(new Predicate[0])).getRestriction();
		};
		Page<Tags> tagsQueryResults = tagsDao.findAll(condition, pageable);
		tagsQueryResults.getContent().forEach(
				tag->tag.setTagsCount(Long.valueOf(tag.getArticles().size()))
		);
		return tagsQueryResults;
	}

	public Tags findTagsById(String id) {
		return tagsDao.findById(id).orElseThrow(ResourceNotFoundException::new);
	}

	public void saveOrUpdate(Tags tags) {
		if (StringUtils.isNotBlank(tags.getId())){
			Tags tempTags = tagsDao.findById(tags.getId()).orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(tempTags, tags);
		}
		tagsDao.save(tags);
	}

	public void deleteBatch(List<String> tagsIds) {
		tagsDao.deleteBatch(tagsIds);
	}


}
