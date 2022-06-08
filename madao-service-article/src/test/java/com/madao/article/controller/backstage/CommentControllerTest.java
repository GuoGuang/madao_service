package com.madao.article.controller.backstage;

import com.madao.article.service.backstage.CommentService;
import com.madao.model.dto.article.CommentDto;
import com.madao.utils.JsonData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.mockito.Mockito.*;

class CommentControllerTest {
	@Mock
	CommentService commentService;
	@InjectMocks
	com.madao.article.controller.backstage.CommentController commentController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testFindCommentByCondition() {
		when(commentService.findCommentByCondition(any(), any())).thenReturn(null);

		JsonData<Page<CommentDto>> result = commentController.findCommentByCondition(new CommentDto(), null);
		Assertions.assertEquals(new JsonData<Page<CommentDto>>(true, 0, "message", any()), result);
	}

	@Test
	void testFindCommentByPrimaryKey() {
		when(commentService.findCommentByPrimaryKey(anyString())).thenReturn(new CommentDto());

		JsonData<CommentDto> result = commentController.findCommentByPrimaryKey("id");
		Assertions.assertEquals(new JsonData<CommentDto>(true, 0, "message", any()), result);
	}

	@Test
	void testInsertComment() {
		JsonData<Void> result = commentController.insertComment(new CommentDto());
		Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
	}

	@Test
	void testUpdateByCommentSelective() {
		JsonData<Void> result = commentController.updateByCommentSelective(new CommentDto());
		Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
	}

	@Test
	void testDeleteByIds() {
		JsonData<Void> result = commentController.deleteByIds(List.<String>of("String"));
		Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
	}
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme