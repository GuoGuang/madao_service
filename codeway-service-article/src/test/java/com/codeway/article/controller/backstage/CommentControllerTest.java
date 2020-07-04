package com.codeway.article.controller.backstage;

import com.codeway.article.service.backstage.CommentService;
import com.codeway.pojo.article.Comment;
import com.codeway.utils.JsonData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class CommentControllerTest {
    @Mock
    CommentService commentService;
    @InjectMocks
    CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindCommentByCondition() {
        when(commentService.findCommentByCondition(any(), any())).thenReturn(null);

        JsonData<Page<Comment>> result = commentController.findCommentByCondition(new Comment(), null);
        Assertions.assertEquals(new JsonData<Page<Comment>>(true, 0, "message", any()), result);
    }

    @Test
    void testFindCommentByPrimaryKey() {
        when(commentService.findCommentByPrimaryKey(anyString())).thenReturn(new Comment());

        JsonData<Comment> result = commentController.findCommentByPrimaryKey("id");
        Assertions.assertEquals(new JsonData<Comment>(true, 0, "message", any()), result);
    }

    @Test
    void testInsertComment() {
        JsonData<Void> result = commentController.insertComment(new Comment());
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }

    @Test
    void testUpdateByCommentSelective() {
        JsonData<Void> result = commentController.updateByCommentSelective(new Comment());
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }

    @Test
    void testDeleteByIds() {
        JsonData<Void> result = commentController.deleteByIds(Arrays.<String>asList("String"));
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme