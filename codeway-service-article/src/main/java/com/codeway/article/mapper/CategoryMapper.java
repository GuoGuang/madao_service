package com.madaoo.article.mapper;

import com.madaoo.model.dto.article.CategoryDto;
import com.madaoo.model.pojo.article.Category;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDto}.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends com.madaoo.article.mapper.EntityMapper<CategoryDto, Category> {

}
