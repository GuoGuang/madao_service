package com.madao.article.controller.blog;

import com.madao.article.service.blog.ApiTagsService;
import com.madao.model.dto.article.TagDto;
import com.madao.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Api(tags = "前台标签管理")
@RestController
@RequestMapping(value = "/api/ar/tag")
public class ApiTagsController {

	private final ApiTagsService apiTagsService;

	public ApiTagsController(ApiTagsService apiTagsService) {
		this.apiTagsService = apiTagsService;
	}

	@ApiOperation(value = "查询标签集合", notes = "Tag")
	@GetMapping
	public JsonData<List<TagDto>> findArticleByCondition(TagDto tagDto,
	                                                     @PageableDefault(sort = "createAt", direction = DESC, value = 1000) Pageable pageable) {
		List<TagDto> result = apiTagsService.findTagsByCondition(tagDto, pageable);
		return JsonData.success(result);
	}

	@ApiOperation(value = "根据id查询标签", notes = "根据id查询标签")
	@GetMapping("/{tagId}")
	public JsonData<TagDto> findTagsById(@PathVariable String tagId) {
		TagDto result = apiTagsService.findTagsById(tagId);
		return JsonData.success(result);
	}
}
