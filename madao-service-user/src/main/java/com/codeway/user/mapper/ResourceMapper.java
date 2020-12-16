package com.madaoo.user.mapper;

import com.madaoo.model.dto.user.ResourceDto;
import com.madaoo.model.pojo.user.Resource;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Resource} and its DTO {@link ResourceDto}.
 */
@Mapper(componentModel = "spring")
public interface ResourceMapper extends com.madaoo.user.mapper.EntityMapper<ResourceDto, Resource> {

}
