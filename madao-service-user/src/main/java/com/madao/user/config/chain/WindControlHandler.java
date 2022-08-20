package com.madao.user.config.chain;

import com.madao.config.chain.AbstractCommonHandler;
import com.madao.model.dto.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 风控校验
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-08-22 22:37
 */
@Slf4j
@Component
public class WindControlHandler extends AbstractCommonHandler<UserDto> {
	@Override
	public void doHandler(UserDto dto) {
		// 风控校验

		checkNextNode(dto);
		log.info("verified");
	}
}