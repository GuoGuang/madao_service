package com.madao.auth.validate;

import com.madao.auth.exception.AuthException;
import com.madao.auth.validate.impl.ValidateCode;
import com.madao.constant.CommonConst;
import com.madao.enums.StatusEnum;
import com.madao.enums.ValidateCodeType;
import com.madao.utils.JsonUtil;
import com.madao.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * redis验证码
 * 将图片验证码或者短信验证码存在redis中
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
        String validateCode = JsonUtil.toJsonString(code);
        redisUtil.setKeyStr(buildKey(request, type), validateCode, CommonConst.TIME_OUT_FIVE_MINUTES.longValue());
        //redisTemplate.opsForValue().set(buildKey(request, type), s, 30, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        String keyStr = redisUtil.getKeyStr(buildKey(request, type))
                .orElseThrow(() -> new AuthException(StatusEnum.CAPTCHA_NOT_MATCH.getMsg()))
                .toString();
        if (keyStr == null) {
            return null;
        }
        return JsonUtil.jsonToPojo(keyStr, ValidateCode.class);
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        //redisUtil.del(buildKey(request, type));
    }

    /**
     * 获取请求头中DEVICE-ID的值，此值与客户端绑定，一端一码
     *
     * @param type 验证码类型：sms or captcha
     */
    private String buildKey(ServletWebRequest request, ValidateCodeType type) {
        String deviceId = request.getHeader("DEVICE-ID");
        if (StringUtils.isBlank(deviceId) && type.toString().equals("CAPTCHA")) {
            throw new AuthException("请在请求头中携带DEVICE-ID参数");
        }
        if (type.toString().equalsIgnoreCase("sms")) {
            String paramName = CommonConst.DEFAULT_PARAMETER_NAME_PHONE;
            String phone = null;
            try {
                phone = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
            } catch (ServletRequestBindingException e) {
                log.error("buildKey失败：{}", StatusEnum.PARAM_MISSING.getMsg(), e);
                throw new RuntimeException(StatusEnum.PARAM_MISSING.getMsg());
            }
            return "code:" + type.toString().toLowerCase() + ":" + phone;
        }
        return "code:" + type.toString().toLowerCase() + ":" + deviceId;
    }

}
