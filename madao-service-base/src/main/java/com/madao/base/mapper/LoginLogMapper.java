package com.madao.base.mapper;

import com.madao.model.dto.base.LoginLogDto;
import com.madao.model.pojo.base.LoginLog;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link LoginLog} and its DTO {@link LoginLogDto}.
 */
@Mapper(componentModel = "spring")
public interface LoginLogMapper extends com.madao.base.mapper.EntityMapper<LoginLogDto, LoginLog> {

}
