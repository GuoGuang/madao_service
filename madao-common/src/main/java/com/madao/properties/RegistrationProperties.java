package com.madao.properties;

/**
 * 社交登录配置项
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class RegistrationProperties {

    private GitHubProperties github = new GitHubProperties();

    private QQProperties qq = new QQProperties();

    /**
     * 社交登录功能拦截的url
     */
//	private String filterProcessesUrl = "/auth";

	public GitHubProperties getGithub() {
		return github;
	}

	public RegistrationProperties setGithub(GitHubProperties github) {
		this.github = github;
		return this;
	}

	public QQProperties getQq() {
		return qq;
	}

	public RegistrationProperties setQq(QQProperties qq) {
		this.qq = qq;
		return this;
	}
}
