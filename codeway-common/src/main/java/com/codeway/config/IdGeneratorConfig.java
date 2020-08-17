package com.codeway.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 自定义Id生成器；
 */
@Configuration
public class IdGeneratorConfig extends UUIDGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
	    Snowflake snowflake = IdUtil.getSnowflake(1, 1);
	    return String.valueOf(snowflake.nextId());
    }
}
