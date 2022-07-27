package com.madao.article.controller.blog;

import com.madao.article.service.blog.ApiTagsService;
import com.madao.model.dto.article.TagDto;
import com.madao.utils.JsonData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Tag(name = "前台标签管理")
@RestController
@RequestMapping(value = "/api/ar/tag")
@AllArgsConstructor
public class ApiTagsController {

	private final ApiTagsService apiTagsService;

	@Operation(summary = "查询标签集合", description = "Tag")
	@GetMapping
	public JsonData<List<TagDto>> findArticleByCondition(TagDto tagDto,
	                                                     @PageableDefault(sort = "createAt", direction = DESC, value = 1000) Pageable pageable) {
		List<TagDto> result = apiTagsService.findTagsByCondition(tagDto, pageable);
		return JsonData.success(result);
	}

	@Operation(summary = "根据id查询标签", description = "根据id查询标签")
	@GetMapping("/{tagId}")
	public JsonData<TagDto> findTagsById(@PathVariable String tagId) {
		TagDto result = apiTagsService.findTagsById(tagId);
		return JsonData.success(result);
	}
}
