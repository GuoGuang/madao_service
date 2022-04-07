/**
 *
 */
package com.madao.auth.validate.impl.sms;

import com.madao.auth.validate.impl.ValidateCode;
import com.madao.auth.validate.impl.ValidateCodeGenerator;
import com.madao.properties.SecurityProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码生成器
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Component
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    public ValidateCode generate(ServletWebRequest request) {
	    String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


}
