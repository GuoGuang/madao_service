package com.ibole.base.controller.backstage;

import com.ibole.base.service.backstage.LabelService;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.base.Label;
import com.ibole.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "标签管理")
@RestController
@RequestMapping("/label")
public class LabelController {

	private final LabelService labelService;

	@Autowired
	public LabelController(LabelService labelService) {
		this.labelService = labelService;
	}

    @GetMapping
    @ApiOperation(value = "按照条件查询全部列表", notes = "Label")
    public JsonData<List<Label>> findLabelByCondition(Label label, QueryVO queryVO) {
        List<Label> result = labelService.findLabelByCondition(label, queryVO);
        return JsonData.success(result);

    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据ID查询标签", notes = "Label")
    public JsonData<Label> findLabelByPrimaryKey(@PathVariable String id) {
        Label result = labelService.findLabelByPrimaryKey(id);
        return JsonData.success(result);
    }

    @PostMapping()
    @ApiOperation(value = "增加标签", notes = "Label")
    public JsonData<Void> insertLabel(@RequestBody @Valid Label label) {
        labelService.insertLabel(label);
        return JsonData.success();
    }

    @PutMapping
    @ApiOperation(value = "修改标签", notes = "Label")
    public JsonData<Void> updateLabel(@RequestBody @Valid Label label) {
        labelService.updateLabel(label);
        return JsonData.success();
    }

    @DeleteMapping
    @ApiOperation(value = "删除标签", notes = "Label")
    public JsonData<Void> deleteById(@RequestBody List<String> labelIds) {
        labelService.deleteById(labelIds);
        return JsonData.success();
    }
}
