package com.madao.enums;

import com.madao.constant.CommonConst;

/**
 * 校验码类型
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public enum ValidateCodeType {

	/**
	 * 短信验证码
	 */
	SMS {
		@Override
		public String getParamNameOnValidate() {
			return CommonConst.DEFAULT_PARAMETER_NAME_CODE_SMS;
		}
	},
	/**
	 * 图片验证码
	 */
	CAPTCHA {
		@Override
		public String getParamNameOnValidate() {
			return CommonConst.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
		}
	};

	/**
	 * 校验时从请求中获取的参数的名字
	 *
	 * @return
	 */
	public abstract String getParamNameOnValidate();

}
