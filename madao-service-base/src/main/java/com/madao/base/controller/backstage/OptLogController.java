package com.madao.base.controller.backstage;

import com.madao.base.service.backstage.OptLogService;
import com.madao.model.dto.base.OptLogDto;
import com.madao.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Api(tags = "操作日志")
@RestController
@RequestMapping("/optLog")
public class OptLogController {

	private final OptLogService optLogService;

	public OptLogController(OptLogService optLogService) {
		this.optLogService = optLogService;
	}

	@GetMapping
	@ApiOperation(value = "按照条件查询全部列表", notes = "OptLog")
	public JsonData<Page<OptLogDto>> findOptLogByCondition(OptLogDto optLogDto,
	                                                       @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<OptLogDto> result = optLogService.findOptLogByCondition(optLogDto, pageable);
		return JsonData.success(result);

	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "根据ID查询操作日志", notes = "OptLog")
	public JsonData<OptLogDto> findOptLogByPrimaryKey(@PathVariable String id) {
		OptLogDto result = optLogService.findById(id);
		return JsonData.success(result);
	}

	@PostMapping()
	@ApiOperation(value = "增加操作日志", notes = "OptLog")
	public JsonData<Void> insertOptLog(@RequestBody OptLogDto optLogDto) {
		optLogService.insertOptLog(optLogDto);
		return JsonData.success();
	}

    @DeleteMapping
    @ApiOperation(value = "删除操作日志", notes = "OptLog")
    public JsonData<Void> deleteById(@RequestBody List<String> optLogIds) {
        optLogService.deleteBatch(optLogIds);
        return JsonData.success();
    }
}
