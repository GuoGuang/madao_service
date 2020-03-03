package com.codeif.auth.config.social;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.github.api.GitHub;

@Configuration
@EnableSocial
public class GitHubConfiguration {

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public GitHub gitHub(ConnectionRepository repository) {
		Connection<GitHub> connection = repository
				.findPrimaryConnection(GitHub.class);
		return connection != null ? connection.getApi() : null;
	}
	@Bean
	public ConnectController connectController(
			ConnectionFactoryLocator factoryLocator,
			ConnectionRepository repository) {
		ConnectController controller = new ConnectController(
				factoryLocator, repository);
		controller.setApplicationUrl("http://localhost:8090");
		return controller;
	}




}

