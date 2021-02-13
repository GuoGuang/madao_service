package com.madao.article.mapper;

import com.madao.article.dao.backstage.CommentDao;
import com.madao.model.dto.article.CommentDto;
import com.madao.model.pojo.article.Comment;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDao}.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<CommentDto, Comment> {

}
