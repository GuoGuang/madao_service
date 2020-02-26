package com.codeif.auth.validate.impl.sms;

import com.codeif.auth.validate.AbstractValidateCodeProcessor;
import com.codeif.auth.validate.impl.ValidateCode;
import com.codeif.constant.CommonConst;
import com.codeif.enums.StatusEnum;
import com.codeif.utils.JsonData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 *
 */
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
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = CommonConst.DEFAULT_PARAMETER_NAME_PHONE;
        String phone = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		request.getResponse().setContentType("application/json;charset=UTF-8");
        try {
	        smsCodeSender.send(phone, validateCode.getCode());
	        request.getResponse().getWriter().write(objectMapper.writeValueAsString(JsonData.success()));
        }catch (Exception ex){
	        request.getResponse().getWriter().write(objectMapper.writeValueAsString(JsonData.failed(StatusEnum.SMS_SEND_ERROR)));

        }
	}

}
