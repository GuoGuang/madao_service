package com.madao.auth.validate;

import com.madao.auth.exception.AuthException;
import com.madao.auth.validate.impl.ValidateCode;
import com.madao.auth.validate.impl.ValidateCodeGenerator;
import com.madao.enums.StatusEnum;
import com.madao.enums.ValidateCodeType;
import com.madao.utils.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;


/**
 * 抽象验证码类
 **/
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements com.madao.auth.validate.ValidateCodeProcessor {

	/**
	 * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;

	@Autowired
	private com.madao.auth.validate.ValidateCodeRepository validateCodeRepository;
	
	@Override
	public void create(ServletWebRequest request) throws ServletRequestBindingException, IOException {
		C validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
	}

	/**
	 * 生成校验码
	 */
	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) {
		String type = getValidateCodeType(request).toString().toLowerCase();
		String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
		if (validateCodeGenerator == null) {
			throw new AuthException("验证码生成器" + generatorName + "不存在");
		}
		return (C) validateCodeGenerator.generate(request);
	}

	/**
	 * 保存校验码
	 */
	private void save(ServletWebRequest request, C validateCode) {
		ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
		validateCodeRepository.save(request, code, getValidateCodeType(request));
	}

	/**
	 * 发送校验码，由子类实现
	 * @param validateCode
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws ServletRequestBindingException, IOException;

	/**
	 * 根据请求的url获取校验码的类型
	 * @param request
	 */
	private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "ValidateCodeProcessor");
		return ValidateCodeType.valueOf(type.toUpperCase());
	}

	/**
	 * 校验验证码实现
	 * @param bodyString 请求体
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(ServletWebRequest request, String bodyString) {

		ValidateCodeType codeType = getValidateCodeType(request);

		C codeInSession = (C) validateCodeRepository.get(request, codeType);

		String codeInRequest;

		Map<String, Object> map = JsonUtil.jsonToMap(bodyString);
		codeInRequest = map.get(codeType.getParamNameOnValidate())+"";

		if (StringUtils.isBlank(codeInRequest)) {
			throw new AuthException(codeType + "验证码的值不能为空");
		}

		if (codeInSession == null) {
			throw new AuthException(codeType + "验证码不存在");
		}

		if (codeInSession.isExpried()) {
			validateCodeRepository.remove(request, codeType);
			throw new AuthException(codeType + "验证码已过期");
		}

		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new AuthException(codeType + StatusEnum.CAPTCHA_NOT_MATCH.getMsg());
		}
		
		validateCodeRepository.remove(request, codeType);
		
	}

}
