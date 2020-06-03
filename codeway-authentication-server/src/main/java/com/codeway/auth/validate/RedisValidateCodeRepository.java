package com.codeway.auth.validate;

import com.codeway.auth.exception.ValidateCodeException;
import com.codeway.auth.validate.impl.ValidateCode;
import com.codeway.constant.CommonConst;
import com.codeway.db.redis.service.RedisService;
import com.codeway.enums.ValidateCodeType;
import com.codeway.utils.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * redis验证码
 * 将图片验证码或者短信验证码存在redis中
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

	@Autowired
	private RedisService redisService;


	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
		String validateCode = JsonUtil.toJsonString(code);
		redisService.setKeyStr(buildKey(request, type), validateCode, CommonConst.TIME_OUT_FIVE_MINUTES.longValue());
		//redisTemplate.opsForValue().set(buildKey(request, type), s, 30, TimeUnit.MINUTES);
	}

	@Override
	public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
		Object keyStr = redisService.getKeyStr(buildKey(request, type));
		if (keyStr == null) {
			return null;
		}
		return JsonUtil.jsonToPojo(keyStr.toString(),ValidateCode.class);
	}

	@Override
	public void remove(ServletWebRequest request, ValidateCodeType type) {
		//redisService.del(buildKey(request, type));
	}

	/**
	 * 获取请求头中DEVICE-ID的值，此值与客户端绑定，一端一码
	 * @param type 验证码类型：sms or captcha
	 */
	private String buildKey(ServletWebRequest request, ValidateCodeType type) {
		String deviceId = request.getHeader("DEVICE-ID");
		if (StringUtils.isBlank(deviceId)) {
			throw new ValidateCodeException("请在请求头中携带DEVICE-ID参数");
		}
		return "code:" + type.toString().toLowerCase() + ":" + deviceId;
	}

}
