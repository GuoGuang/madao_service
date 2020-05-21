package com.codeway.article.controller.backstage;

import com.codeway.annotation.OptLog;
import com.codeway.article.service.backstage.TagsService;
import com.codeway.constant.CommonConst;
import com.codeway.pojo.article.Tags;
import com.codeway.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Api(tags = "标签管理")
@RestController
@RequestMapping(value = "/tags")
public class TagsController {

    private final TagsService tagsService;

    @Autowired
    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @ApiOperation(value = "查询标签集合", notes = "tags")
    @GetMapping
    public JsonData<Page<Tags>> findArticleByCondition(Tags tags,
                                                       @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
        Page<Tags> result = tagsService.findTagsByCondition(tags, pageable);
        return JsonData.success(result);
    }

    @ApiOperation(value = "按照id查询标签", notes = "id")
    @GetMapping(value = "/{id}")
    public JsonData<Tags> findArticleByPrimaryKey(@PathVariable String id) {
        Tags result = tagsService.findTagsById(id);
        return JsonData.success(result);
    }
    @ApiOperation(value = "增加文章标签", notes = "id")
    @PostMapping
    @OptLog(operationType = CommonConst.ADD, operationName = "增加文章标签")
    public JsonData<Void> insertArticle(@RequestBody @Valid Tags tags) {
        tagsService.saveOrUpdate(tags);
        return JsonData.success();
    }

    @ApiOperation(value = "按照id修改", notes = "id")
    @PutMapping
    @OptLog(operationType = CommonConst.MODIFY, operationName = "修改文章标签")
    public JsonData<Void> updateTagsById(@RequestBody @Valid Tags tags) {
        tagsService.saveOrUpdate(tags);
        return JsonData.success();
    }

    @ApiOperation(value = "删除", notes = "id")
    @DeleteMapping
    @OptLog(operationType = CommonConst.DELETE, operationName = "删除文章标签")
    public JsonData<Void> delete(@RequestBody List<String> articleIds) {
        tagsService.deleteBatch(articleIds);
        return JsonData.success();
    }

}
