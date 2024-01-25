package com.madao.article.service.blog;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.json.JsonData;
import com.madao.article.search.ApiArticleSearchDao;
import com.madao.model.dto.article.ArticleSearchDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.highlight.Highlight;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightFieldParameters;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class ApiArticleSearchService {

    private final ApiArticleSearchDao articleSearchDao;

    private final ElasticsearchOperations elasticsearchOperations;

    public List<ArticleSearchDto> searchArticleByCondition(String keywords, Integer page, Integer size) {
        var expectedDate = "2014-10-29";
        var expectedWord = "Scala";
        // 面向对象的查询方式
        var criteria = new Criteria("title")
                .contains(expectedWord)
                .and(
                        new Criteria("startDate").greaterThanEqual(expectedDate)
                );
        var query = new CriteriaQuery(criteria);
        var searchHits = elasticsearchOperations.search(query, ArticleSearchDto.class);
        long totalHits = searchHits.getTotalHits();
        log.info("总数据条数：{}", totalHits);
        return searchHits.getSearchHits().stream().map(SearchHit::getContent).toList();
    }

    /**
     * 通过对象搜索
     */
    public Page<ArticleSearchDto> searchByDtoAndPage(ArticleSearchDto articleSearchDto, Pageable pageable) {

        //创建原生查询DSL对象
        final NativeQueryBuilder nativeQueryBuilder = new NativeQueryBuilder();
        final BoolQuery.Builder boolBuilder = new BoolQuery.Builder();

        //过滤条件
        if (StringUtils.isNotBlank(articleSearchDto.getType())) {
            // 按逗号拆分
            List<Query> industryQueries = Arrays.stream(articleSearchDto.getType().split(",")).map(item -> {
                Query.Builder queryBuilder = new Query.Builder();
                queryBuilder.term(t -> t.field("type").value(item));
                return queryBuilder.build();
            }).collect(Collectors.toList());
            boolBuilder.filter(f -> f.bool(t -> t.should(industryQueries)));
        }
        //范围区间
        if (articleSearchDto.getStartDate() != null) {
            boolBuilder.filter(f -> f.range(r -> r.field("createAt")
                    .gte(JsonData.of(articleSearchDto.getStartDate()))
                    .lte(JsonData.of(articleSearchDto.getEndDate()))));
        }

        //关键字搜索
        if (StringUtils.isNotBlank(articleSearchDto.getKeyword())) {
            setKeyWordAndHighlightField(articleSearchDto, nativeQueryBuilder, boolBuilder);
        }

        nativeQueryBuilder.withPageable(pageable);
        log.info("ES查询语句：{}", nativeQueryBuilder.getQuery());

        var searchHits = elasticsearchOperations.search(nativeQueryBuilder.build(), ArticleSearchDto.class);
        final var list = searchHits.getSearchHits().stream()
                .map(item -> {
                    final var content = item.getContent();
                    content.setTitle(String.join("", item.getHighlightFields().get("title")));
                    return content;
                }).toList();
        return new PageImpl<>(list,pageable,searchHits.getTotalHits());
    }

    /**
     * 原生模式关键字查询
     */
    private void setKeyWordAndHighlightField(ArticleSearchDto articleSearchDto, NativeQueryBuilder nativeQueryBuilder, BoolQuery.Builder boolBuilder) {
        final var keyword = articleSearchDto.getKeyword();
        boolBuilder.must(b -> b.multiMatch(m -> m.fields("title", "content").query(keyword)));
        // 高亮
        final var builder = HighlightFieldParameters.builder()
                .withPreTags("<font color='red'>")
                .withPostTags("</font>")
                .withRequireFieldMatch(true) // 只有在字段匹配时才添加标签
                .withNumberOfFragments(0); // 显示全文
        final var titleHighlight = new Highlight(List.of(new HighlightField("title", builder.build())));

        // 创建函数评分查询，使用 bool 查询构建器中的条件
        nativeQueryBuilder.withQuery(f -> f.functionScore(
                        fs -> fs.query(q -> q.bool(boolBuilder.build()))
                                .functions(
                                        // 标题字段匹配权重为 100.0
                                        FunctionScore.of(func -> func.filter(
                                                fq -> fq.match(ft -> ft.field("title").query(keyword))).weight(100.0)),
                                        // 内容字段匹配权重为 20.0
                                        FunctionScore.of(func -> func.filter(
                                                fq -> fq.match(ft -> ft.field("content").query(keyword))).weight(20.0))
                                )
                                .scoreMode(FunctionScoreMode.Sum)
                                .boostMode(FunctionBoostMode.Sum)
                                .minScore(1.0)))
                .withHighlightQuery(new HighlightQuery(titleHighlight, ArticleSearchDto.class));
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
