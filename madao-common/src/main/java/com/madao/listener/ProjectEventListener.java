package com.madao.listener;

import com.madao.event.LoginSuccessEvent;
import com.madao.model.dto.user.AuthToken;
import com.madao.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;


/**
 * 处理系统中各种事件，通过定义
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022年09月20日00:43:07
 */
@Slf4j
public class ProjectEventListener {

	/**
	 * 登录成功后将此登录人记录到grafana
	 * 参考JHipster的登录成功后事件
	 */
	@EventListener(LoginSuccessEvent.class)
	public void postUpdatedListener(LoginSuccessEvent event) {
		AuthToken extendsParams = event.getExtendsParams();
		log.info("登录成功，记录状态：{}", JsonUtil.toJsonString(extendsParams));
	}
}
