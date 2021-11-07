package com.madao.article.dao.blog;

import com.madao.model.entity.article.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public interface ApiTagsDao extends JpaRepository<Tag, String>, JpaSpecificationExecutor<Tag> {

}
