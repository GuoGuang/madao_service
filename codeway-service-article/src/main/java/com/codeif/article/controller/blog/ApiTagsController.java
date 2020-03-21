package com.codeif.article.controller.blog;

import com.codeif.article.service.blog.ApiTagsService;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.article.Tags;
import com.codeif.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "前台标签管理")
@RestController
@RequestMapping(value = "/api/ar/tags")
public class ApiTagsController {

    private final ApiTagsService apiTagsService;

    @Autowired
    public ApiTagsController(ApiTagsService apiTagsService) {
        this.apiTagsService = apiTagsService;
    }

    @ApiOperation(value = "查询标签集合", notes = "Article")
    @GetMapping
    public JsonData<List<Tags>> findArticleByCondition(Tags tags, QueryVO queryVO) {
        List<Tags> result = apiTagsService.findTagsByCondition();
        return JsonData.success(result);
    }
}
