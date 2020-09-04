package com.codeway.search.dao;

import com.codeway.search.pojo.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {

}
