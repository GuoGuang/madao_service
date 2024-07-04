package com.madao.article.service.backstage;

import com.madao.article.mapper.CommentMapper;
import com.madao.article.repository.backstage.CommentRepository;
import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.model.dto.article.CommentDto;
import com.madao.model.entity.article.Comment;
import com.madao.utils.BeanUtil;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Service
@AllArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final CommentMapper commentMapper;

	public Page<CommentDto> findCommentByCondition(CommentDto commentDto, Pageable pageable) {
		Specification<Comment> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(commentDto.getContent())) {
				predicates.add(builder.like(root.get("content"), "%" + commentDto.getContent() + "%"));
			}
			return query.where(predicates.toArray(new Predicate[0])).getRestriction();
		};
		return commentRepository.findAll(condition, pageable).map(commentMapper::toDto);
	}

	public CommentDto findCommentByPrimaryKey(String commentId) {
		return commentRepository.findById(commentId).map(commentMapper::toDto).orElseThrow(ResourceNotFoundException::new);
	}

	public void saveOrUpdate(CommentDto commentDto) {
		if (StringUtils.isNotBlank(commentDto.getId())) {
			Comment tempComment = commentRepository.findById(commentDto.getId()).orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(tempComment, commentDto);
		}
		commentRepository.save(commentMapper.toEntity(commentDto));
	}

	public void deleteCommentByIds(List<String> commentIds) {
		commentRepository.deleteBatch(commentIds);
	}

}
