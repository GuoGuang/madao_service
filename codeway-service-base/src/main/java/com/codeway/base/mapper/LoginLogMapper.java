package com.codeway.base.mapper;

import com.codeway.model.dto.base.LoginLogDto;
import com.codeway.model.pojo.base.LoginLog;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link LoginLog} and its DTO {@link LoginLogDto}.
 */
@Mapper(componentModel = "spring")
public interface LoginLogMapper extends EntityMapper<LoginLogDto, LoginLog> {

}
