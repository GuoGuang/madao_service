package com.ibole.article.controller.blog;

import com.ibole.article.service.blog.ApiTagsService;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Tags;
import com.ibole.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public JsonData<List<Tags>> findArticleByCondition(Tags tags, QueryVO queryVO) {
        List<Tags> result = apiTagsService.findTagsByCondition();
//        String objMap = "{\"status\":true,\"code\":20000,\"message\":\"操作成功\",\"data\":[{\"updateAt\":\"2019-03-08T18:10:15.000+0000\",\"createAt\":\"2019-01-30T11:07:24.000+0000\",\"id\":1,\"name\":\"2019\",\"slug\":\"2019\",\"description\":\"2019年标签\",\"icon\":\"icon-math\",\"state\":\"1\",\"tagsCount\":2},{\"updateAt\":\"2019-03-08T18:10:11.000+0000\",\"createAt\":\"2019-01-30T11:07:24.000+0000\",\"id\":2,\"name\":\"多肉植物\",\"slug\":\"succulent\",\"description\":\"多肉植物标签\",\"icon\":\"icon-physics\",\"state\":\"1\",\"tagsCount\":null},{\"updateAt\":\"2019-03-08T18:09:58.000+0000\",\"createAt\":\"2019-01-30T11:07:24.000+0000\",\"id\":3,\"name\":\"LeetCode\",\"slug\":\"LeetCode\",\"description\":\"LeetCode标签\",\"icon\":\"icon-physics\",\"state\":\"1\",\"tagsCount\":1}]}";
//        Map<String, Object> objectMap = JsonUtil.jsonToMap(objMap);
        return JsonData.success(result);
    }
}
