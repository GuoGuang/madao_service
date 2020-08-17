package com.codeway.base.mapper;

import com.codeway.model.dto.base.OptLogDto;
import com.codeway.model.pojo.base.OptLog;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link OptLog} and its DTO {@link OptLogDto}.
 */
@Mapper(componentModel = "spring")
public interface OptLogMapper extends EntityMapper<OptLogDto, OptLog> {

}
