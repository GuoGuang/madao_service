package com.madao.article.controller.backstage;

import com.madao.annotation.OptLog;
import com.madao.article.service.backstage.TagsService;
import com.madao.enums.OptLogType;
import com.madao.model.dto.article.TagDto;
import com.madao.utils.JsonData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Tag(name = "标签管理")
@RestController
@RequestMapping(value = "/tags")
@AllArgsConstructor
public class TagsController {

	private final TagsService tagsService;

	@Operation(summary = "查询标签集合", description = "tags")
	@GetMapping
	public JsonData<Page<TagDto>> findArticleByCondition(TagDto tagDto,
	                                                     @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<TagDto> result = tagsService.findTagsByCondition(tagDto, pageable);
		return JsonData.success(result);
	}

	@Operation(summary = "按照id查询标签", description = "id")
	@GetMapping(value = "/{id}")
	public JsonData<TagDto> findArticleByPrimaryKey(@PathVariable String id) {
		TagDto result = tagsService.findTagsById(id);
		return JsonData.success(result);
	}

	@Operation(summary = "增加文章标签", description = "id")
	@PostMapping
	@OptLog(operationType = OptLogType.ADD, operationName = "增加文章标签")
	public JsonData<Void> insertArticle(@RequestBody @Validated TagDto tagDto) {
		tagsService.saveOrUpdate(tagDto);
		return JsonData.success();
	}

	@Operation(summary = "按照id修改", description = "id")
	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "修改文章标签")
	public JsonData<Void> updateTagsById(@RequestBody @Validated TagDto tagDto) {
		tagsService.saveOrUpdate(tagDto);
		return JsonData.success();
	}

	@Operation(summary = "删除", description = "id")
	@DeleteMapping
	@OptLog(operationType = OptLogType.DELETE, operationName = "删除文章标签")
	public JsonData<Void> delete(@RequestBody List<String> articleIds) {
		tagsService.deleteBatch(articleIds);
		return JsonData.success();
	}

}
