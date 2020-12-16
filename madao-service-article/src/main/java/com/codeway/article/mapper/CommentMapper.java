package com.madaoo.article.mapper;

import com.madaoo.article.dao.backstage.CommentDao;
import com.madaoo.model.dto.article.CommentDto;
import com.madaoo.model.pojo.article.Comment;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDao}.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper extends com.madaoo.article.mapper.EntityMapper<CommentDto, Comment> {

}
