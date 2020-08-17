package com.codeway.user.mapper;

import com.codeway.model.dto.user.RoleDto;
import com.codeway.model.pojo.user.Role;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Role} and its DTO {@link RoleDto}.
 */
@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDto, Role> {

}
