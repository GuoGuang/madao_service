package com.ibole.auth.config.social;

import com.ibole.properties.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * github登录配置类
 **/
@ConfigurationProperties(prefix = "spring.social.github")
public class GitHubProperties extends SocialProperties {
}
