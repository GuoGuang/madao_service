package com.codeif.user.controller.blog;

import com.codeif.utils.JsonData;
import com.codeif.utils.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "管理员信息")
@RestController
@RequestMapping(value = "/api/su/admin")
public class AdminController {

    @GetMapping
    @ApiOperation(value = "条件查询资源", notes = "Admin")
    public JsonData<Map<String, Object>> findAdminInfo() {
        String tempInfo = "{\"status\":\"success\",\"message\":\"获取管理员信息成功\",\"result\":{\"name\":\"Surmon\",\"slogan\":\"山河入梦\",\"gravatar\":\"https://static.surmon.me/nodepress/image/WechatIMG8_Fotor_Fotor2.jpg\"}}\n";
        Map<String, Object> objectMap = JsonUtil.jsonToMap(tempInfo);
        return JsonData.success(objectMap);
    }

}
