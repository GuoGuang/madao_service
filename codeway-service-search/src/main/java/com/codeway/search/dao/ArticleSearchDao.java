package com.madaoo.search.dao;

import com.madaoo.search.pojo.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {

}
