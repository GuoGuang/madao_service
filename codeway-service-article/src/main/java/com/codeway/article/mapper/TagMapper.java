package com.codeway.article.mapper;

import com.codeway.model.dto.article.TagDto;
import com.codeway.model.pojo.article.Tag;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Tag} and its DTO {@link TagDto}.
 */
@Mapper(componentModel = "spring")
public interface TagMapper extends EntityMapper<TagDto, Tag> {

}
