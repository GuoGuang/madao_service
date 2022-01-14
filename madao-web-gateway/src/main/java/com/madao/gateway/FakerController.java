package com.madao.gateway;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.github.javafaker.Faker;
import com.madao.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-01-13
 */
@Api(tags = "faker假数据")
@RestController
@RequestMapping(value = "/api/faker")
public class FakerController {

	@GetMapping
	@ApiOperation(value = "根据传入的字段获取数据")
	@ApiImplicitParams(
			@ApiImplicitParam(name = "keys", value = "[\"id\",\"name\",\"age\",\"time\"]", dataType = "List")
	)
	public JsonData<List<HashMap<String, String>>> getList(@RequestBody List<String> keys) {

		List<HashMap<String, String>> results = new ArrayList<>();
		Faker faker = new Faker();
		Snowflake snowflake = IdUtil.getSnowflake(1, 1);
		Map<String, String> fakerMap = keys.stream().collect(Collectors.toMap(item -> item, item -> item));
		for (int i = 0; i < 33; i++) {
			HashMap<String, String> maps = new HashMap<>();
			for (String key : fakerMap.keySet()) {
				if (StringUtils.equalsAnyIgnoreCase("ID", key)) {
					maps.put(key, snowflake.nextIdStr());
				} else if (StringUtils.equalsAnyIgnoreCase("NAME", key)) {
					maps.put(key, faker.name().fullName());
				} else if (StringUtils.equalsAnyIgnoreCase("TIME", key)) {
					maps.put(key, faker.date().birthday().toString());
				} else {
					maps.put(key, faker.animal().name());
				}
			}
			results.add(maps);
		}
		return JsonData.success(results);
	}
}
