package com.codeway.article.service.backstage;

import com.codeway.article.dao.backstage.CommentDao;
import com.codeway.pojo.article.Comment;
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
        Page<Comment> result = commentService.findCommentByCondition(new Comment(), null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindCommentByPrimaryKey() {
        Comment result = commentService.findCommentByPrimaryKey("commentId");
        Assertions.assertEquals(new Comment(), result);
    }

    @Test
    void testSaveOrUpdate() {
        commentService.saveOrUpdate(new Comment());
    }

    @Test
    void testDeleteCommentByIds() {
        commentService.deleteCommentByIds(Arrays.<String>asList("String"));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme