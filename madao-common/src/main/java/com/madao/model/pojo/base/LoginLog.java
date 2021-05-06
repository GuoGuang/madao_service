package com.madao.model.pojo.base;

import com.madao.model.BasePojo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ba_login_log",
        indexes = {
                @Index(name = "login_log_client_ip", columnList = "clientIp"),
                @Index(name = "login_log_user_id", columnList = "userId"),
                @Index(name = "login_log_create_at", columnList = "createAt")
        })
public class LoginLog extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
    private String id;

    @Column(length = 20)
    private String userId;

    @Column(length = 20)
    private String userName;

    @Column(length = 20)
    private String clientIp;

    @Column(length = 50)
    private String browser;

    @Column(length = 100)
    private String osInfo;

	public String getId() {
		return id;
	}

	public LoginLog setId(String id) {
		this.id = id;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public LoginLog setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public LoginLog setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getClientIp() {
		return clientIp;
	}

	public LoginLog setClientIp(String clientIp) {
		this.clientIp = clientIp;
		return this;
	}

	public String getBrowser() {
		return browser;
	}

	public LoginLog setBrowser(String browser) {
		this.browser = browser;
		return this;
	}

	public String getOsInfo() {
		return osInfo;
	}

	public LoginLog setOsInfo(String osInfo) {
		this.osInfo = osInfo;
		return this;
	}
}