//
//package com.madao.article.service.blog;
//
//import com.madao.article.search.ApiArticleSearchDao;
//import com.madao.model.dto.article.ArticleSearchDto;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.SearchHit;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Service
//@AllArgsConstructor
//@Slf4j
//public class ApiArticleSearchService {
//
//	private final ApiArticleSearchDao articleSearchDao;
//	private final ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//	public List<ArticleSearchDto> searchArticleByCondition(String keywords, Integer page, Integer size) {
//
////			多条件
////			RangeQueryBuilder queryStartDate = QueryBuilders.rangeQuery("startTime").gte("111").lte("");
////			RangeQueryBuilder queryEndDate = QueryBuilders.rangeQuery("endTime").gte("111").lte("");
////			MatchQueryBuilder name = QueryBuilders.matchQuery("name", "xxx");
////			QueryBuilder queryBuilder = QueryBuilders.boolQuery()
////					.must(queryStartDate)
////					.must(queryEndDate)
////					.must(name);
////			Query nativeSearchQuery = new NativeSearchQuery(queryBuilder);
////			nativeSearchQuery.setPageable(pageable);
////			SearchHits<ArticleSearchDto> results = elasticsearchRestTemplate.search(nativeSearchQuery, ArticleSearchDto.class);
////			List<SearchHit<ArticleSearchDto>> searchHits = results.getSearchHits();
//
//		QueryBuilder queryBuilder = QueryBuilders.termQuery("user", "kimchy");
//
//		HighlightBuilder highlightBuilder = new HighlightBuilder()
//				.field("title")
//				.field("content")
//				.preTags("<span style='color:red'>")
//				.postTags("</span>")
//				// 如果要高亮显示的字段内容很多,需要如下配置,避免高亮显示不全、内容缺失
//				.fragmentSize(1000) // 最大高亮分片数
//				.numOfFragments(0);// 从第一个分片获取高亮片段
//
////	    AvgAggregationBuilder priceAvgAggregation = AggregationBuilders.avg("avgPrice").field("price");
//
//		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
//				.withQuery(queryBuilder)
//				.withPageable(PageRequest.of(page, size))
//				// 指定查询结果包含那些文档字段
//				.withFields("author", "name", "price", "commentCount")
//				// 查询结果如何排序
////			    .withSort(new FieldSortBuilder("price").order(SortOrder.ASC))
//				// 设置查询结果高亮
//				.withHighlightBuilder(highlightBuilder)
//				// 添加聚合查询
////			    .addAggregation(priceAvgAggregation)
//				.build();
//
//		SearchHits<ArticleSearchDto> search = elasticsearchRestTemplate.search(nativeSearchQuery, ArticleSearchDto.class);
//
//		long totalHits = search.getTotalHits();
//		log.info("总数据条数：{}", totalHits);
//		List<ArticleSearchDto> results = search.getSearchHits().stream().map(SearchHit::getContent).toList();
//		return results;
//	}
//
//	public ArticleSearchDto findArticleByPrimaryKey(String id) {
//		return articleSearchDao.findById(id).orElse(null);
//	}
//
//	public void insertArticle(ArticleSearchDto article) {
//		articleSearchDao.save(article);
//	}
//
//	public void updateByPrimaryKey(ArticleSearchDto article) {
//		articleSearchDao.save(article);
//	}
//
//	public void deleteById(String id) {
//		articleSearchDao.deleteById(id);
//	}
//}
