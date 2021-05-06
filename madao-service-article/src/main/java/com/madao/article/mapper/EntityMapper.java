package com.madao.article.mapper;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}
