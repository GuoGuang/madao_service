
package com.madao.article.service.blog;

import com.madao.article.search.ApiArticleSearchDao;
import com.madao.model.dto.article.ArticleSearchDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class ApiArticleSearchService {

	private final ApiArticleSearchDao articleSearchDao;

	private final ElasticsearchOperations elasticsearchOperations;

	public List<ArticleSearchDto> searchArticleByCondition(String keywords, Integer page, Integer size) {

//			多条件
//			RangeQueryBuilder queryStartDate = QueryBuilders.rangeQuery("startTime").gte("111").lte("");
//			RangeQueryBuilder queryEndDate = QueryBuilders.rangeQuery("endTime").gte("111").lte("");
//			MatchQueryBuilder name = QueryBuilders.matchQuery("name", "xxx");
//			QueryBuilder queryBuilder = QueryBuilders.boolQuery()
//					.must(queryStartDate)
//					.must(queryEndDate)
//					.must(name);
//			Query nativeSearchQuery = new NativeSearchQuery(queryBuilder);
//			nativeSearchQuery.setPageable(pageable);
//			SearchHits<ArticleSearchDto> results = elasticsearchRestTemplate.search(nativeSearchQuery, ArticleSearchDto.class);
//			List<SearchHit<ArticleSearchDto>> searchHits = results.getSearchHits();
		Query query = NativeQuery.builder()
				.withQuery(q -> q
						.match(m -> m
								.field("user")
								.query("firstName")
						)
				)
//				.withPageable(pageable)
				.build();

		SearchHits<ArticleSearchDto> searchHits = elasticsearchOperations.search(query, ArticleSearchDto.class);
		long totalHits = searchHits.getTotalHits();
		log.info("总数据条数：{}", totalHits);
		List<ArticleSearchDto> results = searchHits.getSearchHits().stream().map(SearchHit::getContent).toList();
		return results;
	}

	public ArticleSearchDto findArticleByPrimaryKey(String id) {
		return articleSearchDao.findById(id).orElse(null);
	}

	public void insertArticle(ArticleSearchDto article) {
		articleSearchDao.save(article);
	}

	public void updateByPrimaryKey(ArticleSearchDto article) {
		articleSearchDao.save(article);
	}

	public void deleteById(String id) {
		articleSearchDao.deleteById(id);
	}
}
