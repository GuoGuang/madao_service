package com.madaoo.article.mapper;

import com.madaoo.model.dto.article.ArticleDto;
import com.madaoo.model.pojo.article.Article;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Article} and its DTO {@link ArticleDto}.
 */
@Mapper(componentModel = "spring")
public interface ArticleMapper extends com.madaoo.article.mapper.EntityMapper<ArticleDto, Article> {

}
