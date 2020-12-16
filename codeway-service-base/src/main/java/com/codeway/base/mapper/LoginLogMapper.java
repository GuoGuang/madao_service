package com.madaoo.base.mapper;

import com.madaoo.model.dto.base.LoginLogDto;
import com.madaoo.model.pojo.base.LoginLog;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link LoginLog} and its DTO {@link LoginLogDto}.
 */
@Mapper(componentModel = "spring")
public interface LoginLogMapper extends com.madaoo.base.mapper.EntityMapper<LoginLogDto, LoginLog> {

}
