package com.madao.article.mapper;

import com.madao.model.dto.article.ArticleDto;
import com.madao.model.pojo.article.Article;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Article} and its DTO {@link ArticleDto}.
 */
@Mapper(componentModel = "spring")
public interface ArticleMapper extends com.madao.article.mapper.EntityMapper<ArticleDto, Article> {

}
