package com.madao.article.mapper;

import com.madao.model.dto.article.TagDto;
import com.madao.model.pojo.article.Tag;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Tag} and its DTO {@link TagDto}.
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Mapper(componentModel = "spring")
public interface TagMapper extends EntityMapper<TagDto, Tag> {

}
