package com.madao.base.controller.backstage;

import com.madao.base.service.backstage.OptLogService;
import com.madao.model.dto.base.OptLogDto;
import com.madao.utils.JsonData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@Tag(name = "操作日志")
@RestController
@RequestMapping("/optLog")
@AllArgsConstructor
public class OptLogController {

	private final OptLogService optLogService;

	@GetMapping
	@Operation(summary = "按照条件查询全部列表", description = "OptLog")
	public JsonData<Page<OptLogDto>> findOptLogByCondition(OptLogDto optLogDto,
	                                                       @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<OptLogDto> result = optLogService.findOptLogByCondition(optLogDto, pageable);
		return JsonData.success(result);

	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "根据ID查询操作日志", description = "OptLog")
	public JsonData<OptLogDto> findOptLogByPrimaryKey(@PathVariable String id) {
		OptLogDto result = optLogService.findById(id);
		return JsonData.success(result);
	}

	@PostMapping()
	@Operation(summary = "增加操作日志", description = "OptLog")
	public JsonData<Void> insertOptLog(@RequestBody OptLogDto optLogDto) {
		optLogService.insertOptLog(optLogDto);
		return JsonData.success();
	}

	@DeleteMapping
	@Operation(summary = "删除操作日志", description = "OptLog")
	public JsonData<Void> deleteById(@RequestBody List<String> optLogIds) {
		optLogService.deleteBatch(optLogIds);
		return JsonData.success();
	}
}
