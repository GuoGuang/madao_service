package com.madao.auth.validate.impl.sms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madao.auth.validate.AbstractValidateCodeProcessor;
import com.madao.auth.validate.impl.ValidateCode;
import com.madao.constant.CommonConst;
import com.madao.enums.StatusEnum;
import com.madao.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * 短信验证码处理器
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Component
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws ServletRequestBindingException, IOException {
        String paramName = CommonConst.DEFAULT_PARAMETER_NAME_PHONE;
        String phone = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		request.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
	        smsCodeSender.send(phone, validateCode.getCode());
	        request.getResponse().getWriter().write(objectMapper.writeValueAsString(JsonData.success()));
        }catch (Exception ex){
	        log.error("向手机:{}，{},原因:{}",phone,StatusEnum.SMS_SEND_ERROR.getMsg(),ex.getCause(),ex);
	        request.getResponse().getWriter().write(objectMapper.writeValueAsString(JsonData.failed(StatusEnum.SMS_SEND_ERROR)));

        }
	}

}
