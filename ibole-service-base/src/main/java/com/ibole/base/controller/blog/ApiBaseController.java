package com.ibole.base.controller.blog;

import com.ibole.utils.JsonData;
import com.ibole.utils.JsonUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "基础接口")
@RestController
@RequestMapping(value = "/api/base")
public class ApiBaseController {

    @GetMapping("/announcement")
    public JsonData<Map<String, Object>> findAnnouncement() {
        String hashMap = "{\"status\":\"success\",\"message\":\"获取公告成功\",\"result\":{\"data\":[],\"params\":{\"querys\":{\"state\":1},\"options\":{\"sort\":{\"_id\":-1},\"page\":1},\"params\":{\"url\":\"/announcement\"},\"isAuthenticated\":false},\"pagination\":{\"total\":0,\"current_page\":1,\"total_page\":1,\"per_page\":16}}}";
        Map<String, Object> objMap = JsonUtil.jsonToMap(hashMap);
        return JsonData.success(objMap);
    }

}
