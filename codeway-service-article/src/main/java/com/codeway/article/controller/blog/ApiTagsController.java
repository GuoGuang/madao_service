package com.codeway.article.controller.blog;

import com.codeway.article.service.blog.ApiTagsService;
import com.codeway.pojo.QueryVO;
import com.codeway.pojo.article.Article;
import com.codeway.pojo.article.Tags;
import com.codeway.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import static org.springframework.data.domain.Sort.Direction.DESC;

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
    public JsonData<List<Tags>> findArticleByCondition(Tags tags,
                                                       @PageableDefault(sort = "createAt", direction = DESC, value = 1000) Pageable pageable) {
        List<Tags> result = apiTagsService.findTagsByCondition(tags, pageable);
        return JsonData.success(result);
    }

    @ApiOperation(value = "根据id查询标签", notes = "根据id查询标签")
    @GetMapping("/{tagId}")
    public JsonData<Tags> findTagsById(@PathVariable String tagId) {
        Tags result = apiTagsService.findTagsById(tagId);
        return JsonData.success(result);
    }
}
