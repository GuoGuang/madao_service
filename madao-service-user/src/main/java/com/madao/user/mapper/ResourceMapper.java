package com.madao.user.mapper;

import com.madao.model.dto.user.ResourceDto;
import com.madao.model.pojo.user.Resource;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Resource} and its DTO {@link ResourceDto}.
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Mapper(componentModel = "spring")
public interface ResourceMapper extends EntityMapper<ResourceDto, Resource> {

}
