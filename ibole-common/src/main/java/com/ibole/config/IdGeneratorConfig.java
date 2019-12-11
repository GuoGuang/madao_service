package com.ibole.config;

import com.ibole.utils.IdGenerate;
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
        IdGenerate idGenerate = new IdGenerate(1, 1);
        return String.valueOf(idGenerate.nextId());
    }
}
