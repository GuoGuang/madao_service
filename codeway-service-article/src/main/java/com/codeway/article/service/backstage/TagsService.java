package com.codeway.article.service.backstage;

import com.codeway.article.dao.backstage.TagsDao;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.pojo.article.Tag;
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
public class TagsService {

	private final TagsDao tagsDao;

	public TagsService(TagsDao tagsDao) {
		this.tagsDao = tagsDao;
	}

	public Page<Tag> findTagsByCondition(Tag tags, Pageable pageable) {
		Specification<Tag> condition = (root, query, builder) -> {
			List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(tags.getName())) {
				predicates.add(builder.like(root.get("name"), "%" + tags.getName() + "%"));
			}
			if (tags.getState() != null) {
				predicates.add(builder.equal(root.get("state"), tags.getState()));
			}
			return query.where(predicates.toArray(new Predicate[0])).getRestriction();
		};
		Page<Tag> tagsQueryResults = tagsDao.findAll(condition, pageable);
		tagsQueryResults.getContent().forEach(
				tag -> tag.setTagsCount(Long.valueOf(tag.getArticles().size()))
		);
		return tagsQueryResults;
	}

	public Tag findTagsById(String id) {
		return tagsDao.findById(id).orElseThrow(ResourceNotFoundException::new);
	}

	public void saveOrUpdate(Tag tags) {
		if (StringUtils.isNotBlank(tags.getId())) {
			Tag tempTags = tagsDao.findById(tags.getId()).orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(tempTags, tags);
		}
		tagsDao.save(tags);
	}

	public void deleteBatch(List<String> tagsIds) {
		tagsDao.deleteBatch(tagsIds);
	}


}
