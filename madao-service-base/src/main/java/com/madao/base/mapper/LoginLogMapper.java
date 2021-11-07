package com.madao.base.mapper;

import com.madao.model.dto.base.LoginLogDto;
import com.madao.model.entity.base.LoginLog;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link LoginLog} and its DTO {@link LoginLogDto}.
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Mapper(componentModel = "spring")
public interface LoginLogMapper extends com.madao.base.mapper.EntityMapper<LoginLogDto, LoginLog> {

}
