package com.codeway.article.dao.blog;

import com.codeway.pojo.QueryVO;
import com.codeway.pojo.article.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;


/**
 * 标签
 **/


public interface ApiTagsDao extends JpaRepository<Tags, String>, JpaSpecificationExecutor<Tags> {

    @Query(value = "SELECT *  FROM ar_tags at LEFT JOIN ( SELECT tags_id, count( * ) AS  tags_count FROM ar_article_tags  GROUP BY  tags_id ) aat ON at.id = aat.tags_id"
            , nativeQuery = true)
    ArrayList<Tags> findTagsByCondition(QueryVO queryVO);
}
