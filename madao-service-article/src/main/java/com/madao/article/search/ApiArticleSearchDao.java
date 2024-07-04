package com.madao.article.search;

import com.madao.model.dto.article.ArticleSearchDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created by guoguang0536@gmail.com / 1831682775@qq.com
 * @created 2022/01/23/ 22:37:00
 */
public interface ApiArticleSearchDao extends ElasticsearchRepository<ArticleSearchDto, String> {

    List<ArticleSearchDto> findByTitle(String title);
}
