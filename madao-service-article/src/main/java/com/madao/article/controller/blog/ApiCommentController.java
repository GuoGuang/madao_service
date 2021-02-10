package com.madao.article.controller.blog;

import com.madao.article.mapper.CommentMapper;
import com.madao.article.service.blog.ApiCommentService;
import com.madao.model.dto.article.CommentDto;
import com.madao.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api(tags = "前台评论管理")
@RestController
@RequestMapping(value = "/api/ar/comment")
public class ApiCommentController {

    private final ApiCommentService apiCommentService;
    private final CommentMapper commentMapper;

    public ApiCommentController(ApiCommentService apiCommentService, CommentMapper commentMapper) {
        this.apiCommentService = apiCommentService;
        this.commentMapper = commentMapper;
    }

    @ApiOperation(value = "查询我的评论")
    @GetMapping("/my/")
    public JsonData<List<HashMap<Object, Object>>> findMyComment(@CurrentSecurityContext Authentication authentication) {
        return JsonData.success(apiCommentService.findMyComment(authentication.getName()));
    }

    @ApiOperation(value = "查询评论列表", notes = "查询评论列表")
    @GetMapping("/{articleId:\\d+}")
    public JsonData<List<CommentDto>> findArticleByCondition(@PathVariable String articleId) {
        List<CommentDto> result = apiCommentService.findCommentByCondition(articleId);
        return JsonData.success(result);
    }

    @ApiOperation(value = "点赞评论", notes = "点赞评论")
    @PutMapping(value = "/like/{commentId}")
    public JsonData<Void> upVote(@PathVariable String commentId) {
        apiCommentService.upVote(commentId);
        return JsonData.success();
    }

    @ApiOperation(value = "取消点赞", notes = "取消点赞")
    @DeleteMapping(value = "/like/{commentId}")
    public JsonData<Void> unUpVote(@PathVariable String commentId) {
        apiCommentService.unUpVote(commentId);
        return JsonData.success();
    }

    @ApiOperation(value = "回复评论/添加新评论", notes = "回复评论/添加新评论")
    @PostMapping
    public JsonData<Void> addComment(@RequestBody @Validated CommentDto commentDto) {
        apiCommentService.addComment(commentMapper.toEntity(commentDto));
        return JsonData.success();
    }

}
