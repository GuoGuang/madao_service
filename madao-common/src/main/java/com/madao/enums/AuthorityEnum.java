package com.madao.enums;

import com.madao.constant.UserConst;

/**
 * 权限相关
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public enum AuthorityEnum {

	/**
	 * 管理员
	 */
	ROLE_ADMIN {
		@Override
		public String getParamNameOnAuthority() {
			return UserConst.ROLE_ADMIN;
		}
	},

	/**
	 * 访客
	 */
	ROLE_GUEST {
		@Override
		public String getParamNameOnAuthority() {
			return UserConst.ROLE_GUEST;
		}
	};

	public abstract String getParamNameOnAuthority();

}
