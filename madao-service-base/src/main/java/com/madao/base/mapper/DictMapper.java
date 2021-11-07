package com.madao.base.mapper;

import com.madao.model.dto.base.DictDto;
import com.madao.model.entity.base.Dict;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Dict} and its DTO {@link DictDto}.
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Mapper(componentModel = "spring")
public interface DictMapper extends com.madao.base.mapper.EntityMapper<DictDto, Dict> {

}
