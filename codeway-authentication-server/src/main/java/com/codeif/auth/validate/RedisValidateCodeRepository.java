package com.codeif.auth.validate;

import com.codeif.auth.exception.ValidateCodeException;
import com.codeif.auth.validate.impl.ValidateCode;
import com.codeif.db.redis.service.RedisService;
import com.codeif.utils.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 基于redis的验证码存取器，避免由于没有session导致无法存取验证码的问题
 * 
 * @author zhailiang
 *
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

	@Autowired
	private RedisService redisService;


	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
		String validateCode = JsonUtil.toJsonString(code);
		redisService.setKeyStr(buildKey(request, type), validateCode);
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
	 * @param request
	 * @param type
	 * @return
	 */
	private String buildKey(ServletWebRequest request, ValidateCodeType type) {
		String deviceId = request.getHeader("DEVICE-ID");
		if (StringUtils.isBlank(deviceId)) {
			throw new ValidateCodeException("请在请求头中携带DEVICE-ID参数");
		}
		return "code:" + type.toString().toLowerCase() + ":" + deviceId;
	}

}
