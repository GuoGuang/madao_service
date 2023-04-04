package com.madao.config;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 自定义Id生成器
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Configuration
public class IdGeneratorConfig extends UUIDGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
		return String.valueOf(IdGenerator.generateId());
	}


}
