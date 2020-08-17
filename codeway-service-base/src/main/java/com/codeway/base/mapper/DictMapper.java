package com.codeway.base.mapper;

import com.codeway.model.dto.base.DictDto;
import com.codeway.model.pojo.base.Dict;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Dict} and its DTO {@link DictDto}.
 */
@Mapper(componentModel = "spring")
public interface DictMapper extends EntityMapper<DictDto, Dict> {

}
