package com.madaoo.article.mapper;

import com.madaoo.model.dto.article.TagDto;
import com.madaoo.model.pojo.article.Tag;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Tag} and its DTO {@link TagDto}.
 */
@Mapper(componentModel = "spring")
public interface TagMapper extends com.madaoo.article.mapper.EntityMapper<TagDto, Tag> {

}
