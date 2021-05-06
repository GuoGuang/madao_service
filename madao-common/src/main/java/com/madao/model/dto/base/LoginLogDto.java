package com.madao.model.dto.base;

import com.madao.model.BasePojo;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;

public class LoginLogDto extends BasePojo implements Serializable {

    @ApiModelProperty("登录日志表主键")
    private String id;

    @ApiModelProperty("登录人")
    private String userId;

    @Transient
    @ApiModelProperty("登录人名称")
    private String userName;

    @ApiModelProperty("登录ip")
    private String clientIp;

    @ApiModelProperty("浏览器")
    private String browser;

    @ApiModelProperty("操作系统信息")
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
		if (!(o instanceof LoginLogDto)) return false;
		if (!super.equals(o)) return false;
		LoginLogDto that = (LoginLogDto) o;
		return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(userName, that.userName) && Objects.equals(clientIp, that.clientIp) && Objects.equals(browser, that.browser) && Objects.equals(osInfo, that.osInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, userId, userName, clientIp, browser, osInfo);
	}
}