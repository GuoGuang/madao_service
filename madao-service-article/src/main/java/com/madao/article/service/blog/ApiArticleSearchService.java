package com.madao.article.service.blog;

import co.elastic.clients.elasticsearch._types.FieldValue;
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

    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    private final ApiArticleSearchDao articleSearchDao;

    private final ElasticsearchOperations elasticsearchOperations;


    /**
     * 查询所有数据
     */
    public List<ArticleSearchDto> findAll(Pageable pageable) {
        final var nativeQueryBuilder = new NativeQueryBuilder();
        final var query = new MatchAllQuery.Builder();
        nativeQueryBuilder.withPageable(pageable);
        nativeQueryBuilder.withQuery(query.build()._toQuery());
        var searchHits = elasticsearchOperations.search(nativeQueryBuilder.build(), ArticleSearchDto.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent).toList();
    }

    /**
     * 精确查询term
     */
    public List<ArticleSearchDto> findByTerm(ArticleSearchDto articleSearchDto, Pageable pageable) {
        final var nativeQueryBuilder = new NativeQueryBuilder();
        final var query = new TermQuery.Builder();
        query.field(TITLE).value(articleSearchDto.getTitle());
        nativeQueryBuilder.withPageable(pageable);
        nativeQueryBuilder.withQuery(query.build()._toQuery());
        var searchHits = elasticsearchOperations.search(nativeQueryBuilder.build(), ArticleSearchDto.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent).toList();
    }

    /**
     * 精确查询terms
     */
    public List<ArticleSearchDto> findByTerms(List<String> title, Pageable pageable) {
        final var nativeQueryBuilder = new NativeQueryBuilder();
        final var query = new TermsQuery.Builder();
        query.field(TITLE)
                .terms(term ->
                        term.value(
                                title.stream()
                                .map(FieldValue::of)
                                .collect(Collectors.toList())
                        )
                );
        nativeQueryBuilder.withPageable(pageable);
        nativeQueryBuilder.withQuery(query.build()._toQuery());
        var searchHits = elasticsearchOperations.search(nativeQueryBuilder.build(), ArticleSearchDto.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent).toList();
    }
    /**
     * 范围查询range
     */
    public List<ArticleSearchDto> findByRangeQuery(Integer value, Pageable pageable) {
        final var nativeQueryBuilder = new NativeQueryBuilder();
        final var query = new RangeQuery.Builder();
        query.field("thumbUp")
                .gte(JsonData.of(value))
                .lte(JsonData.of(100))
                .queryName("这是一个query的名称，张三test");
        nativeQueryBuilder.withPageable(pageable);
        nativeQueryBuilder.withQuery(query.build()._toQuery());
        var searchHits = elasticsearchOperations.search(nativeQueryBuilder.build(), ArticleSearchDto.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent).toList();
    }
    /**
     * 通配符查询
     */
    public List<ArticleSearchDto> findByWildcardQuery(String value, Pageable pageable) {
        final var nativeQueryBuilder = new NativeQueryBuilder();
        final var query = new WildcardQuery.Builder();
        query.field(TITLE)
                .wildcard("*" + value + "*");
        nativeQueryBuilder.withPageable(pageable);
        nativeQueryBuilder.withQuery(query.build()._toQuery());
        var searchHits = elasticsearchOperations.search(nativeQueryBuilder.build(), ArticleSearchDto.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent).toList();
    }


    /**
     * prefix_query 前缀查询
     */
    public List<ArticleSearchDto> findByPrefixQuery(String title, Pageable pageable) {
        final var nativeQueryBuilder = new NativeQueryBuilder();
        final var query = new PrefixQuery.Builder();
        query.field(TITLE)
                .value(title);
        nativeQueryBuilder.withPageable(pageable);
        nativeQueryBuilder.withQuery(query.build()._toQuery());
        var searchHits = elasticsearchOperations.search(nativeQueryBuilder.build(), ArticleSearchDto.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent).toList();
    }


    /**
     * fuzzy_query 模糊查询
     */
    public List<ArticleSearchDto> findByFuzzyQuery(String content, Pageable pageable) {
        final var nativeQueryBuilder = new NativeQueryBuilder();
        final var query = new FuzzyQuery.Builder();
        query.field(CONTENT)
                .value(content)
                .fuzziness("AUTO") // 计算编辑距离
                // 设置前缀长度，如果设置 prefixLength 为 3，那么在计算编辑距离时将忽略查询字符串的前三个字符。
                // 例如，对于查询字符串 "apple"，在计算编辑距离时将忽略 "app" 部分。
                // 如果 prefix_length 设置为0，并且 max_expansions 设置为一个高数字，这个查询可能非常重量级。它可能导致索引中的每个术语被检查！
                .prefixLength(1)
                .maxExpansions(10); // 设置最大扩展数
        nativeQueryBuilder.withPageable(pageable);
        nativeQueryBuilder.withQuery(query.build()._toQuery());
        var searchHits = elasticsearchOperations.search(nativeQueryBuilder.build(), ArticleSearchDto.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent).toList();
    }
    /**
     * match_phrase 短语查询
     */
    public List<ArticleSearchDto> findByMatchPhrase(String content, Pageable pageable) {
        final var nativeQueryBuilder = new NativeQueryBuilder();
        final var query = new MatchPhraseQuery.Builder();
        query.field(CONTENT)
                .query(content)
                .analyzer("ik_max_word")
                .slop(0); // 设置允许的词语之间的最大偏移量
        nativeQueryBuilder.withPageable(pageable);
        nativeQueryBuilder.withQuery(query.build()._toQuery());
        var searchHits = elasticsearchOperations.search(nativeQueryBuilder.build(), ArticleSearchDto.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent).toList();
    }

    /**
     * 匹配查询
     */
    public List<ArticleSearchDto> findByMatch(String context, Pageable pageable) {
        final var nativeQueryBuilder = new NativeQueryBuilder();
        final var query = new MatchQuery.Builder();
        query.field(CONTENT)
                .query(context)
                .analyzer("ik_max_word") // 指定分词器
                .operator(Operator.And) // 指定匹配操作符，可以是 "and" 或 "or"。默认为 "or"，表示只需匹配查询字符串中的一个词即可
                .fuzziness("auto") // 指定模糊匹配的类型，可以是 "auto", "0..n", "0..m", "n", "m"。默认为 "auto"，表示根据查询字符串的长度自动选择
                .prefixLength(1) // 指定在模糊匹配时要忽略的前缀长度
                .maxExpansions(50); // 指定模糊搜索时的最大扩展数。
        nativeQueryBuilder.withPageable(pageable);
        nativeQueryBuilder.withQuery(query.build()._toQuery());
        var searchHits = elasticsearchOperations.search(nativeQueryBuilder.build(), ArticleSearchDto.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent).toList();
    }

    /**
     * filter
     * 我们在查询数据的时候，返回的结果中，所有字段都给我们返回了，但是有时候我们并不需要那么多，所以可以对结果进行过滤处理。
     * filter的使用方式比较多样，下面用几个例子演示一下。
     */
    public List<ArticleSearchDto> findByFilter(String title, Pageable pageable) {
        final var nativeQueryBuilder = new NativeQueryBuilder();

        // filter与must基本一样，不同的是filter不计算评分，效率更高。
//        final var filter = new Query.Builder();
//        filter.bool(query -> query.filter(ff -> {
//            ff.term(qq -> qq.field(TITLE).value(title));
//            return ff;
//        }));


        final var query = new BoolQuery.Builder();
        query.must(b -> b.match(m -> m.field(CONTENT).query(title)));
        query.filter(filter -> filter.bool(bool ->
                        bool.must(m1 -> m1.term(term -> term.field(TITLE).value(title)))
                            .must(m2 -> m2.term(term -> term.field(CONTENT).value(title)))
                )
        );
        nativeQueryBuilder.withPageable(pageable);
        nativeQueryBuilder.withQuery(query.build()._toQuery());
        var searchHits = elasticsearchOperations.search(nativeQueryBuilder.build(), ArticleSearchDto.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent).toList();
    }


    /**
     * 方式一：面向对象的查询方式，即自定义查询
     */
    public List<ArticleSearchDto> searchArticleByCondition(String keywords, Integer page, Integer size) {
        var expectedDate = "2014-10-29";
        var expectedWord = "Scala";
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
     * 方式二：JPA命名的方式查询
     */
    public List<ArticleSearchDto> findByTitle(String title) {
       return articleSearchDao.findByTitle(title);
    }

    /**
     * 方式三：原生查询DSL对象
     */
    public Page<ArticleSearchDto> searchByDtoAndPage(ArticleSearchDto articleSearchDto, Pageable pageable) {

        final var nativeQueryBuilder = new NativeQueryBuilder();
        final var boolBuilder = new BoolQuery.Builder();

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
                    if (item.getHighlightFields() != null && !item.getHighlightFields().isEmpty()){
                        if (item.getHighlightFields().get(TITLE) != null){
                            content.setTitle(String.join("", item.getHighlightFields().get(TITLE)));
                        }
                        if (item.getHighlightFields().get(CONTENT) != null){
                            content.setContent(String.join("", item.getHighlightFields().get(CONTENT)));
                        }
                    }
                    return content;
                }).toList();
        return new PageImpl<>(list, pageable, searchHits.getTotalHits());
    }

    /**
     * 原生模式关键字查询
     */
    private void setKeyWordAndHighlightField(ArticleSearchDto articleSearchDto, NativeQueryBuilder nativeQueryBuilder, BoolQuery.Builder boolBuilder) {
        final var keyword = articleSearchDto.getKeyword();
        boolBuilder.must(b -> b.multiMatch(m -> m.fields(TITLE, CONTENT).query(keyword)));
        // 高亮
        final var builder = HighlightFieldParameters.builder()
                .withPreTags("<font color='red'>")
                .withPostTags("</font>")
                .withRequireFieldMatch(true) // 只有在字段匹配时才添加标签
                .withNumberOfFragments(0); // 显示全文
        final var titleHighlight = new Highlight(List.of(new HighlightField(TITLE, builder.build()),new HighlightField(CONTENT, builder.build())));

        // 创建函数评分查询，查询时优先考虑权重高的字段_scope
        nativeQueryBuilder.withQuery(f -> f.functionScore(
                        fs -> fs.query(q -> q.bool(boolBuilder.build()))
                                .functions(
                                        // 标题字段匹配权重为 100.0
                                        FunctionScore.of(func -> func.filter(
                                                fq -> fq.match(ft -> ft.field(TITLE).query(keyword))).weight(100.0)),
                                        // 内容字段匹配权重为 20.0
                                        FunctionScore.of(func -> func.filter(
                                                fq -> fq.match(ft -> ft.field("content").query(keyword))).weight(20.0))
                                )
                                .scoreMode(FunctionScoreMode.Sum)
                                .boostMode(FunctionBoostMode.Sum)
                                .minScore(1.0) // 设置最小得分，文档得分低于这个值的将被排除
                                .boost(120.0f))// 调整查询结果的排序
                )
                .withHighlightQuery(new HighlightQuery(titleHighlight, ArticleSearchDto.class));
    }


    public ArticleSearchDto findArticleByPrimaryKey(String id) {
        return articleSearchDao.findById(id).orElse(null);
    }

    public void saveOrUpdateArticle(ArticleSearchDto article) {
        articleSearchDao.save(article);
    }

    public void deleteById(String id) {
        articleSearchDao.deleteById(id);
    }
}
