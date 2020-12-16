package com.madaoo.article.controller.backstage;

import com.madaoo.annotation.OptLog;
import com.madaoo.article.service.backstage.TagsService;
import com.madaoo.enums.OptLogType;
import com.madaoo.model.dto.article.TagDto;
import com.madaoo.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
	public JsonData<Page<TagDto>> findArticleByCondition(TagDto tagDto,
	                                                     @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<TagDto> result = tagsService.findTagsByCondition(tagDto, pageable);
		return JsonData.success(result);
	}

	@ApiOperation(value = "按照id查询标签", notes = "id")
	@GetMapping(value = "/{id}")
	public JsonData<TagDto> findArticleByPrimaryKey(@PathVariable String id) {
		TagDto result = tagsService.findTagsById(id);
		return JsonData.success(result);
	}

	@ApiOperation(value = "增加文章标签", notes = "id")
	@PostMapping
	@OptLog(operationType = OptLogType.ADD, operationName = "增加文章标签")
	public JsonData<Void> insertArticle(@RequestBody @Validated TagDto tagDto) {
		tagsService.saveOrUpdate(tagDto);
		return JsonData.success();
	}

	@ApiOperation(value = "按照id修改", notes = "id")
	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "修改文章标签")
	public JsonData<Void> updateTagsById(@RequestBody @Validated TagDto tagDto) {
		tagsService.saveOrUpdate(tagDto);
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
