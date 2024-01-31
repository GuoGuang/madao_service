package com.madao.article;

import com.madao.annotation.EnableSpringCloudComponent;
import com.madao.article.search.ApiArticleSearchDao;
import com.madao.config.BasicApplication;
import com.madao.model.dto.article.ArticleSearchDto;
import com.madao.utils.DateUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

/**
 * SpringBoot主配置类只会扫描自己所在的包及其子包下面,需要通过@ComponentScan处理
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@EnableSpringCloudComponent
@EnableJpaRepositories("com.madao.article.dao")
@EnableElasticsearchRepositories("com.madao.article.search")
public class ArticleApplication extends BasicApplication {

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;
	@Autowired
	private ApiArticleSearchDao articleSearchDao;

	public static void main(String[] args) {
		SpringApplication.run(ArticleApplication.class, args);
	}

	/**
	 * 服务启动完成后初始化示例数据
	 */
	@PostConstruct
	public void insertDataSample() {
		articleSearchDao.deleteAll();
		var documents = Arrays.asList(
				ArticleSearchDto.builder().startDate(DateUtil.dateStringToDate("2014-11-06")).title("Spring Boot 中文文档").isPublic(false).content("Spring Boot是由Pivotal团队提供的一套开源框架，可以简化spring应用的创建及部署。它提供了丰富的Spring模块化支持，可以帮助开发者更轻松快捷地构建出企业级应用。Spring Boot通过自动配置功能，降低了复杂性，同时支持基于JVM的多种开源框架，可以缩短开发时间，使开发更加简单和高效。").build(),
				ArticleSearchDto.builder().startDate(DateUtil.dateStringToDate("2015-12-07")).title("Spring Security 中文文档").isPublic(false).content("Spring Security是一个Java框架，用于保护应用程序的安全性。它提供了一套全面的安全解决方案，包括身份验证、授权、防止攻击等功能。Spring Security基于过滤器链的概念，可以轻松地集成到任何基于Spring的应用程序中。它支持多种身份验证选项和授权策略，开发人员可以根据需要选择适合的方式。此外，Spring Security还提供了一些附加功能，如集成第三方身份验证提供商和单点登录，以及会话管理和密码编码等。总之，Spring Security是一个强大且易于使用的框架，可以帮助开发人员提高应用程序的安全性和可靠性。").build(),
				ArticleSearchDto.builder().startDate(DateUtil.dateStringToDate("2016-11-20")).title("Spring Data 中文文档").isPublic(true).content("Spring Data 是 Spring 框架的一个子项目，旨在简化与各种数据存储技术（如关系型数据库、NoSQL数据库、图数据库等）的集成和操作。它提供了一种统一的编程模型和API，使开发人员能够以一致的方式访问和操作不同类型的数据存储。Spring Data 通过提供通用的 CRUD 操作、查询方法、事务管理和数据访问抽象层等功能，简化了数据访问层的开发工作。它还提供了与 Spring 框架其他模块（如Spring Boot、Spring MVC等）的无缝集成，使开发人员能够更轻松地构建全栈应用程序。").build(),
				ArticleSearchDto.builder().startDate(DateUtil.dateStringToDate("2011-11-12")).title("Spring Cloud Bus 中文文档").isPublic(false).content("Spring Cloud Bus将分布式系统的节点与一个轻量级的 message broker 联系起来。然后，这个 broker 可以用来广播状态变化（如配置变化）或其他管理指令。一个关键的想法是，总线（bus）就像Spring Boot应用的一个分布式执行器，是可以扩展的。然而，它也可以被用作应用程序之间的通信 channel。这个项目提供了 AMQP broker 或 Kafka 作为传输的 starter。").build(),
				ArticleSearchDto.builder().startDate(DateUtil.dateStringToDate("2024-10-04")).title("Spring Batch 中文文档").isPublic(true).content("Spring Batch 是一个轻量级的开源框架，它提供了一种简单的方式来处理大量的数据。它基于Spring框架，提供了一套批处理框架，可以处理各种类型的批处理任务，如ETL、数据导入/导出、报表生成等。Spring Batch提供了一些重要的概念，如Job、Step、ItemReader、ItemProcessor、ItemWriter等，这些概念可以帮助我们构建可重用的批处理应用程序。通过Spring Batch，我们可以轻松地实现批处理的并发、容错、重试等功能，同时也可以方便地与其他Spring组件集成，如Spring Boot、Spring Data等。总之，Spring Batch是一个非常强大、灵活、易于使用的批处理框架，可以帮助我们快速构建高效、可靠的批处理应用程序。").build()
		);
		articleSearchDao.saveAll(documents);
	}
}
