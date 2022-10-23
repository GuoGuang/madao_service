package com.madao.base.controller.backstage;

import com.madao.utils.JsonData;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class QueueDemo {

	@Autowired
	RedisQueryService redisQueryService;

	@GetMapping("/push")
	public JsonData<Object> a(String idCard, String doctorId) {
		if (redisQueryService.isExistZSET(doctorId, idCard)) {
			return JsonData.success("您已在排队中，请勿重复排队");
		}
		redisQueryService.push(doctorId, idCard);
		return JsonData.success(getSelfLocation(idCard, doctorId));
	}

	@GetMapping("/getSelfLocation")
	public QueryUser getSelfLocation(String idCard, String doctorId) {

		Long selfLocation = redisQueryService.getSelfLocation(doctorId, idCard);
		Long curQueryNumber = redisQueryService.curQueryNumber(doctorId);

		long location = (curQueryNumber == 1 || curQueryNumber == 0) ? 0 : curQueryNumber - (curQueryNumber - selfLocation + 1);
		return new QueryUser(selfLocation, curQueryNumber, location, Math.round((location * 1.4) * 60) + "秒");
	}

	@GetMapping("/locationState")
	public JsonData<Long> locationState(String doctorId) {
		return JsonData.success(redisQueryService.curQueryNumber(doctorId));
	}

	@GetMapping("/cancelSelf")
	public JsonData<String> cancelSelf(String idCard, String doctorId) {
		redisQueryService.remove(doctorId, idCard);
		return JsonData.success("取消排队成功");
	}


}
