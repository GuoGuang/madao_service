package com.madao.user.mapper;

import com.madao.model.dto.user.UserDto;
import com.madao.model.pojo.user.User;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link User} and its DTO {@link UserDto}.
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends com.madao.user.mapper.EntityMapper<UserDto, User> {

}
