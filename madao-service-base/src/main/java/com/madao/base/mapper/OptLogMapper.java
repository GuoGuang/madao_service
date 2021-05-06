package com.madao.base.mapper;

import com.madao.model.dto.base.OptLogDto;
import com.madao.model.pojo.base.OptLog;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link OptLog} and its DTO {@link OptLogDto}.
 */
@Mapper(componentModel = "spring")
public interface OptLogMapper extends com.madao.base.mapper.EntityMapper<OptLogDto, OptLog> {

}
