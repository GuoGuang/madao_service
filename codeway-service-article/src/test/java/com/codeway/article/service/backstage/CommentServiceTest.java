package com.codeway.article.service.backstage;

import com.codeway.article.dao.backstage.CommentDao;
import com.codeway.model.dto.article.CommentDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.Arrays;

class CommentServiceTest {
    @Mock
    CommentDao commentDao;
    @InjectMocks
    CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindCommentByCondition() {
	    Page<CommentDto> result = commentService.findCommentByCondition(new CommentDto(), null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindCommentByPrimaryKey() {
	    CommentDto result = commentService.findCommentByPrimaryKey("commentId");
	    Assertions.assertEquals(new CommentDto(), result);
    }

    @Test
    void testSaveOrUpdate() {
	    commentService.saveOrUpdate(new CommentDto());
    }

    @Test
    void testDeleteCommentByIds() {
        commentService.deleteCommentByIds(Arrays.<String>asList("String"));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme