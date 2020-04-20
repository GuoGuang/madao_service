package com.codeway.auth.config.social;

import com.codeway.properties.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * github登录配置类
 **/
@ConfigurationProperties(prefix = "spring.social.github")
public class GitHubProperties extends SocialProperties {
}
