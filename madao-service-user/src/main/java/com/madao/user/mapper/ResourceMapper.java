package com.madao.user.mapper;

import com.madao.model.dto.user.ResourceDto;
import com.madao.model.pojo.user.Resource;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Resource} and its DTO {@link ResourceDto}.
 */
@Mapper(componentModel = "spring")
public interface ResourceMapper extends EntityMapper<ResourceDto, Resource> {

}
