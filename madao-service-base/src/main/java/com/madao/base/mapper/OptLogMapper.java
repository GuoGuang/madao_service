package com.madao.base.mapper;

import com.madao.model.dto.base.OptLogDto;
import com.madao.model.pojo.base.OptLog;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link OptLog} and its DTO {@link OptLogDto}.
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Mapper(componentModel = "spring")
public interface OptLogMapper extends com.madao.base.mapper.EntityMapper<OptLogDto, OptLog> {

}
