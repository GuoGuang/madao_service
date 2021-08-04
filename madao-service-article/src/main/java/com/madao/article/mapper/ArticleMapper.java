package com.madao.article.mapper;

import com.madao.model.dto.article.ArticleDto;
import com.madao.model.entity.article.Article;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Article} and its DTO {@link ArticleDto}.
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Mapper(componentModel = "spring")
public interface ArticleMapper extends EntityMapper<ArticleDto, Article> {

}
