package com.madao.base.mapper;

import com.madao.model.dto.base.DictDto;
import com.madao.model.pojo.base.Dict;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Dict} and its DTO {@link DictDto}.
 */
@Mapper(componentModel = "spring")
public interface DictMapper extends com.madao.base.mapper.EntityMapper<DictDto, Dict> {

}
