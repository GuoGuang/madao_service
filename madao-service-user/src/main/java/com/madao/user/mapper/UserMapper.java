package com.madao.user.mapper;

import com.madao.model.dto.user.UserDto;
import com.madao.model.pojo.user.User;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link User} and its DTO {@link UserDto}.
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

}
