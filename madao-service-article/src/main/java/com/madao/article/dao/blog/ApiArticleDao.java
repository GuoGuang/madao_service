package com.madao.article.dao.blog;

import com.madao.model.pojo.article.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public interface ApiArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

    /**
     * 获取随机六条文章作为推荐数据
     */
    @Modifying
    @Query(value = "SELECT * FROM ar_article ORDER BY rand() LIMIT 6",
            nativeQuery = true)
    List<Article> findRelatedByRand();

//	@Query(value = "SELECT aa.*,ac.name,ac.id as cid FROM ar_article aa LEFT JOIN ar_category ac on aa.category_id = ac.id",
//					nativeQuery = true)
//	Page<Article> findArticlePage(Page page, QueryVO queryVO);

    /**
     * 审核文章
     */
//	void examine(String id);

    /**
     * 点赞
     */
    @Modifying
    @Query("update Article a set upvote = upvote+1 where id=:id")
    void updateUpVote(@Param("id") String id);

    @Modifying
    @Query("update Article a set upvote = upvote-1 where id=:id")
    void updateUnUpVote(@Param("id") String id);

    @Query(value = "SELECT * FROM ar_article WHERE id in (SELECT article_id FROM ar_article_tag WHERE tag_id = :tagId)", nativeQuery = true)
    Page<Article> findArticleByTagId(String tagId, Pageable pageable);

    @Query("SELECT COUNT(upvote)as articles,SUM(upvote) as upvote ,SUM(visits)as visits FROM Article ")
    Object[] findAuthorDetail();

    default Map<String, Object> findAuthorDetailByMap() {
        Object[] authorDetails = (Object[]) findAuthorDetail()[0];
        HashMap<String, Object> authorDetail = new HashMap<>();
        authorDetail.put("articles", authorDetails[0]);
        authorDetail.put("upvote", authorDetails[1]);
        authorDetail.put("visits", authorDetails[2]);
        return authorDetail;
    }
}
