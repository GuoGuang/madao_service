package com.codeway.article.controller.blog;

import com.codeway.article.service.blog.ApiCommentService;
import com.codeway.pojo.article.Comment;
import com.codeway.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Api(tags = "前台评论管理")
@RestController
@RequestMapping(value = "/api/ar/comment")
public class ApiCommentController {

    private final ApiCommentService apiCommentService;

    public ApiCommentController(ApiCommentService apiCommentService) {
        this.apiCommentService = apiCommentService;
    }

    @ApiOperation(value = "查询评论列表", notes = "Article")
    @GetMapping
    public JsonData<List<Comment>> findArticleByCondition(@PageableDefault(sort = "createAt", direction = DESC, value = 1000) Pageable pageable) {
        List<Comment> result = apiCommentService.findCommentByCondition(pageable);
        return JsonData.success(result);
    }

    @ApiOperation(value = "点赞评论", notes = "Article")
    @PutMapping(value = "/like/{commentId}")
    public JsonData<Void> upVote(@PathVariable String commentId) {
        apiCommentService.upVote(commentId);
        return JsonData.success();
    }

    @ApiOperation(value = "取消点赞", notes = "Article")
    @DeleteMapping(value = "/like/{commentId}")
    public JsonData<Void> unUpVote(@PathVariable String commentId) {
        apiCommentService.unUpVote(commentId);
        return JsonData.success();
    }

    @ApiOperation(value = "回复评论/添加新评论", notes = "Article")
    @PostMapping
    public JsonData<Void> addComment(@RequestBody Comment comment) {
        apiCommentService.addComment(comment);
        return JsonData.success();
    }

}
