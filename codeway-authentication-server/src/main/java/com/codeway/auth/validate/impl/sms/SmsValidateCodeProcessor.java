package com.codeway.auth.validate.impl.sms;

import com.codeway.auth.validate.AbstractValidateCodeProcessor;
import com.codeway.auth.validate.impl.ValidateCode;
import com.codeway.constant.CommonConst;
import com.codeway.enums.StatusEnum;
import com.codeway.utils.JsonData;
import com.codeway.utils.LogBack;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * 短信验证码处理器
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
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws ServletRequestBindingException, IOException {
        String paramName = CommonConst.DEFAULT_PARAMETER_NAME_PHONE;
        String phone = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		request.getResponse().setContentType("application/json;charset=UTF-8");
        try {
	        smsCodeSender.send(phone, validateCode.getCode());
	        request.getResponse().getWriter().write(objectMapper.writeValueAsString(JsonData.success()));
        }catch (Exception ex){
	        LogBack.error("向手机:{}，{},原因:{}",phone,StatusEnum.SMS_SEND_ERROR.getMsg(),ex.getCause(),ex);
	        request.getResponse().getWriter().write(objectMapper.writeValueAsString(JsonData.failed(StatusEnum.SMS_SEND_ERROR)));

        }
	}

}
