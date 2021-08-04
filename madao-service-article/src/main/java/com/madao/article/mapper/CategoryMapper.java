package com.madao.article.mapper;

import com.madao.model.dto.article.CategoryDto;
import com.madao.model.entity.article.Category;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDto}.
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDto, Category> {

}
