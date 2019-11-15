package com.ibole.auth.validate.impl.captcha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibole.auth.validate.AbstractValidateCodeProcessor;
import com.ibole.enums.StatusEnum;
import com.ibole.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;

/**
 * 图片验证码处理器
 * 
 * @author zhailiang
 *
 */
@Component
public class CaptchaValidateCodeProcessor extends AbstractValidateCodeProcessor<Captcha> {

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * 发送图形验证码，将其写到响应中
	 */
	@Override
	protected void send(ServletWebRequest request, Captcha captcha) throws Exception {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		ImageIO.write(captcha.getImage(),"JPEG", outputStream);
		String base64Code = Base64.getEncoder().encodeToString(outputStream.toByteArray());

		HashMap<Object, Object> map = new HashMap<>();
		map.put("deviceId",request.getHeader("DEVICE-ID"));
		map.put("base64Code",base64Code);

		JsonData jsonData = new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), map);

		request.getResponse().setContentType("application/json;charset=UTF-8");
		request.getResponse().getWriter().write(objectMapper.writeValueAsString(jsonData));

	}

}
