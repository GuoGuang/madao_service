package com.madao.user.mapper;

import com.madao.model.dto.user.RoleDto;
import com.madao.model.entity.user.Role;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Role} and its DTO {@link RoleDto}.
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDto, Role> {

}
