package com.madaoo.user.mapper;

import com.madaoo.model.dto.user.UserDto;
import com.madaoo.model.pojo.user.User;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link User} and its DTO {@link UserDto}.
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends com.madaoo.user.mapper.EntityMapper<UserDto, User> {

}
