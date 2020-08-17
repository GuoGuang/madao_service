package com.codeway.user.mapper;

import com.codeway.model.dto.user.UserDto;
import com.codeway.model.pojo.user.User;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link User} and its DTO {@link UserDto}.
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

}
