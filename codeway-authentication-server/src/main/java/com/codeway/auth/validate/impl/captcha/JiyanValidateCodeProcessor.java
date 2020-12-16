package com.madaoo.auth.validate.impl.captcha;

import com.madaoo.auth.validate.AbstractValidateCodeProcessor;
import com.madaoo.utils.LogBack;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 极验验证码
 */
@Component
public class JiyanValidateCodeProcessor extends AbstractValidateCodeProcessor<com.madaoo.auth.validate.impl.captcha.Captcha> {

	@Override
	protected void send(ServletWebRequest request, com.madaoo.auth.validate.impl.captcha.Captcha validateCode) {
		// non send
	}

	@Override
	public void validate(ServletWebRequest request, String bodyString) {
		LogBack.info("极验验证");
	}
}
