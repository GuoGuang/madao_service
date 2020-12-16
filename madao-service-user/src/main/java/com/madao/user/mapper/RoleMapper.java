package com.madao.user.mapper;

import com.madao.model.dto.user.RoleDto;
import com.madao.model.pojo.user.Role;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Role} and its DTO {@link RoleDto}.
 */
@Mapper(componentModel = "spring")
public interface RoleMapper extends com.madao.user.mapper.EntityMapper<RoleDto, Role> {

}
