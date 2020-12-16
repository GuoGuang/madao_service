package com.madaoo.base.mapper;

import com.madaoo.model.dto.base.OptLogDto;
import com.madaoo.model.pojo.base.OptLog;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link OptLog} and its DTO {@link OptLogDto}.
 */
@Mapper(componentModel = "spring")
public interface OptLogMapper extends com.madaoo.base.mapper.EntityMapper<OptLogDto, OptLog> {

}
