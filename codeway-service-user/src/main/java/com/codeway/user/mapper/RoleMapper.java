package com.madaoo.user.mapper;

import com.madaoo.model.dto.user.RoleDto;
import com.madaoo.model.pojo.user.Role;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Role} and its DTO {@link RoleDto}.
 */
@Mapper(componentModel = "spring")
public interface RoleMapper extends com.madaoo.user.mapper.EntityMapper<RoleDto, Role> {

}
