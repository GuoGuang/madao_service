package com.madaoo.base.mapper;

import com.madaoo.model.dto.base.DictDto;
import com.madaoo.model.pojo.base.Dict;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Dict} and its DTO {@link DictDto}.
 */
@Mapper(componentModel = "spring")
public interface DictMapper extends com.madaoo.base.mapper.EntityMapper<DictDto, Dict> {

}
