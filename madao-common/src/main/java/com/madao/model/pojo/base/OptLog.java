package com.madao.model.pojo.base;

import com.madao.enums.OptLogType;
import com.madao.model.BasePojo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ba_opt_log",
        indexes = {
                @Index(name = "opt_log_client_ip", columnList = "clientIp"),
                @Index(name = "opt_log_user_id", columnList = "userId"),
                @Index(name = "opt_log_create_at", columnList = "createAt")
        })
public class OptLog extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
    private String id;

    @Column(length = 20)
    private String userId;

    @Transient
    private String userName;

    @Column(length = 20)
    private String clientIp;

    @Column(length = 1)
    private OptLogType type;

    @Column(length = 100)
    private String method;

    @Column(length = 500)
    private String params;

    @Column(length = 500)
    private String exceptionDetail;

    @Column(length = 50)
    private String browser;

    @Column(length = 50)
    private String osInfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public OptLogType getType() {
		return type;
	}

	public void setType(OptLogType type) {
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getExceptionDetail() {
		return exceptionDetail;
	}

	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOsInfo() {
		return osInfo;
	}

	public void setOsInfo(String osInfo) {
		this.osInfo = osInfo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OptLog)) return false;
		if (!super.equals(o)) return false;
		OptLog optLog = (OptLog) o;
		return Objects.equals(id, optLog.id) && Objects.equals(userId, optLog.userId) && Objects.equals(userName, optLog.userName) && Objects.equals(clientIp, optLog.clientIp) && type == optLog.type && Objects.equals(method, optLog.method) && Objects.equals(params, optLog.params) && Objects.equals(exceptionDetail, optLog.exceptionDetail) && Objects.equals(browser, optLog.browser) && Objects.equals(osInfo, optLog.osInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, userId, userName, clientIp, type, method, params, exceptionDetail, browser, osInfo);
	}
}