package com.madao.config;

import com.madao.model.dto.article.ArticleSearchDto;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.index.AliasAction;
import org.springframework.data.elasticsearch.core.index.AliasActionParameters;
import org.springframework.data.elasticsearch.core.index.AliasActions;

/**
 * ES配置
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created by guoguang0536@gmail.com / 1831682775@qq.com
 * @created 2021/11/23/ 22:09:00
 */
@Configuration
@AllArgsConstructor
public class SearchClientConfig {

    private final ElasticsearchOperations elasticsearchOperations;

    /**
     * 初始化ES索引的别名
     */
    @PostConstruct
    public void initAlias() {
        IndexOperations indexOps = elasticsearchOperations.indexOps(ArticleSearchDto.class);
        indexOps.alias(
                new AliasActions()
                        .add(new AliasAction.Add(
                                        AliasActionParameters.builder()
                                                .withIndices("article_search")
                                                .withAliases("article_se_v1")
                                                .build()
                                )
                        ).add(new AliasAction.Add(
                                        AliasActionParameters.builder()
                                                .withIndices("article_search")
                                                .withAliases("article_se_v2")
                                                .build()
                                )
                        )
        );
    }

}
