package com.madao.auth.validate.impl.captcha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madao.auth.validate.AbstractValidateCodeProcessor;
import com.madao.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

/**
 * 图片验证码处理器
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Component
public class CaptchaValidateCodeProcessor extends AbstractValidateCodeProcessor<Captcha> {

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * 发送图形验证码，将其写到响应中
	 */
	@Override
	protected void send(ServletWebRequest request, Captcha captcha) throws IOException {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		ImageIO.write(captcha.getImage(), "JPEG", outputStream);
		String base64Code = Base64.getEncoder().encodeToString(outputStream.toByteArray());

		HashMap<Object, Object> map = new HashMap<>();
		map.put("deviceId", request.getHeader("DEVICE-ID"));
		map.put("base64Code", base64Code);

		request.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		request.getResponse().getWriter().write(objectMapper.writeValueAsString(JsonData.success(map)));

	}

}
