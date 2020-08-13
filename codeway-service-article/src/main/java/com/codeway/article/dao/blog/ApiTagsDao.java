package com.codeway.article.dao.blog;

import com.codeway.model.QueryVO;
import com.codeway.model.pojo.article.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ApiTagsDao extends JpaRepository<Tag, String>, JpaSpecificationExecutor<Tag> {

	@Query(value = "SELECT *  FROM ar_tags at LEFT JOIN ( SELECT tag_id, count( * ) AS  tags_count FROM ar_article_tags  GROUP BY  tag_id ) aat ON at.id = aat.tag_id"
			, nativeQuery = true)
	ArrayList<Tag> findTagsByCondition(QueryVO queryVO);
}
