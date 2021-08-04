package com.madao.article.mapper;

import com.madao.article.dao.backstage.CommentDao;
import com.madao.model.dto.article.CommentDto;
import com.madao.model.entity.article.Comment;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDao}.
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<CommentDto, Comment> {

}
