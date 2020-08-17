package com.codeway.article.mapper;

import com.codeway.model.dto.article.ArticleDto;
import com.codeway.model.pojo.article.Article;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Article} and its DTO {@link ArticleDto}.
 */
@Mapper(componentModel = "spring")
public interface ArticleMapper extends EntityMapper<ArticleDto, Article> {

}
