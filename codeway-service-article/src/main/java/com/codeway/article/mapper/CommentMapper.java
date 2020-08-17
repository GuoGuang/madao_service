package com.codeway.article.mapper;

import com.codeway.article.dao.backstage.CommentDao;
import com.codeway.model.dto.article.CommentDto;
import com.codeway.model.pojo.article.Comment;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDao}.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<CommentDto, Comment> {

}
