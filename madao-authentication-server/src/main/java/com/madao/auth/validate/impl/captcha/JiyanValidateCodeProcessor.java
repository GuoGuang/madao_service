package com.madao.auth.validate.impl.captcha;

import com.madao.auth.validate.AbstractValidateCodeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 极验验证码
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Component
public class JiyanValidateCodeProcessor extends AbstractValidateCodeProcessor<Captcha> {

	@Override
	protected void send(ServletWebRequest request, Captcha validateCode) {
		// non send
	}

	@Override
	public void validate(ServletWebRequest request, String bodyString) {
		log.info("极验验证");
	}
}
