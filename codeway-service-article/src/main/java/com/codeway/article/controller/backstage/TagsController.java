package com.codeway.article.controller.backstage;

import com.codeway.annotation.OptLog;
import com.codeway.article.service.backstage.TagsService;
import com.codeway.enums.OptLogType;
import com.codeway.model.pojo.article.Tag;
import com.codeway.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

	@ApiOperation(value = "查询标签集合", notes = "tags")
	@GetMapping
	public JsonData<Page<Tag>> findArticleByCondition(Tag tags,
	                                                  @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<Tag> result = tagsService.findTagsByCondition(tags, pageable);
		return JsonData.success(result);
	}

	@ApiOperation(value = "按照id查询标签", notes = "id")
	@GetMapping(value = "/{id}")
	public JsonData<Tag> findArticleByPrimaryKey(@PathVariable String id) {
		Tag result = tagsService.findTagsById(id);
		return JsonData.success(result);
	}

	@ApiOperation(value = "增加文章标签", notes = "id")
	@PostMapping
	@OptLog(operationType = OptLogType.ADD, operationName = "增加文章标签")
	public JsonData<Void> insertArticle(@RequestBody @Valid Tag tags) {
		tagsService.saveOrUpdate(tags);
		return JsonData.success();
	}

	@ApiOperation(value = "按照id修改", notes = "id")
	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "修改文章标签")
	public JsonData<Void> updateTagsById(@RequestBody @Valid Tag tags) {
		tagsService.saveOrUpdate(tags);
		return JsonData.success();
	}

    @ApiOperation(value = "删除", notes = "id")
    @DeleteMapping
    @OptLog(operationType = OptLogType.DELETE, operationName = "删除文章标签")
    public JsonData<Void> delete(@RequestBody List<String> articleIds) {
        tagsService.deleteBatch(articleIds);
        return JsonData.success();
    }

}
