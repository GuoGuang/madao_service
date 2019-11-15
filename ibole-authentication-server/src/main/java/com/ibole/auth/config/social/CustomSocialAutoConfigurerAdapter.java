package com.ibole.auth.config.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.github.connect.GitHubConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

/**
 * 自定义添加连接器工厂解决Social包在SpringBoot2.x移除问题
 * @see https://www.jianshu.com/p/e6de152a0b4e
 * @see https://blog.csdn.net/melodykke/article/details/83862959
 **/
@Configuration
//@ConditionalOnProperty(prefix = "spring.social.github")
@EnableConfigurationProperties(GitHubProperties.class)
public class CustomSocialAutoConfigurerAdapter  extends SocialConfigurerAdapter {

	@Autowired
	private GitHubProperties properties;

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
		super.addConnectionFactories(connectionFactoryConfigurer, environment);

		connectionFactoryConfigurer.addConnectionFactory(createConnectionFactory());

	}

	public ConnectionFactory<?> createConnectionFactory() {
		return new GitHubConnectionFactory(
				properties.getAppId(),
				properties.getAppSecret());
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	// 后补：做到处理注册逻辑的时候发现的一个bug：登录完成后，数据库没有数据，但是再次登录却不用注册了
    // 就怀疑是否是在内存中存储了。结果果然发现这里父类的内存ConnectionRepository覆盖了SocialConfig中配置的jdbcConnectionRepository
    // 这里需要返回null，否则会返回内存的 ConnectionRepository



}
