package com.madao.article.mapper;

import com.madao.model.dto.article.CategoryDto;
import com.madao.model.pojo.article.Category;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDto}.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDto, Category> {

}
