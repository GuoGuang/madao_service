package com.youyd.config;


import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * mybatis配置
 * @author zhaoxd
 *
 */
@Configuration
//扫描多个工程下的dao
@MapperScan("com.youyd.**.dao")
public class MyBatisConfig{

    @Autowired
    private DataSource dataSource;


   // @Bean(name = "sqlSessionFactory") 这句话开启 会导致 Table 'xxx' doesn't exist
    public SqlSessionFactory sqlSessionFactoryBean() {
    	// 集成mybatis-plus需要将原来的SqlSessionFactory 换成MybatisSqlSessionFactoryBean
	    MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        bean.setDataSource(dataSource);
        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
/*
	@Bean("mybatisSqlSession")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		sqlSessionFactory.setDataSource(dataSource);
		//*注册Map 下划线转驼峰*
		configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());

		sqlSessionFactory.setConfiguration(configuration);
		//...其他配置
		return sqlSessionFactory.getObject();
	}*/

}