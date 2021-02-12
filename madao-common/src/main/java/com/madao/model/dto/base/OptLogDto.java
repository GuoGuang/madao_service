package com.madao.model.dto.base;

import com.google.common.base.Objects;
import com.madao.enums.OptLogType;
import com.madao.model.BasePojo;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class OptLogDto extends BasePojo implements Serializable {

    @ApiModelProperty("操作日志表主键")
    private String id;

    @ApiModelProperty("操作人id")
    private String userId;

    @ApiModelProperty("操作人名称")
    private String userName;

    @ApiModelProperty("操作ip")
    private String clientIp;

    @ApiModelProperty(value = "操作类型（1：增，2：删，3：改）")
    private OptLogType type;

    @ApiModelProperty("操作方法名称")
    private String method;

    @ApiModelProperty("操作方法的参数（json）")
    private String params;

    @ApiModelProperty("异常详情")
    private String exceptionDetail;

    @ApiModelProperty("浏览器")
    private String browser;

    @ApiModelProperty("操作系统信息")
    private String osInfo;

	public String getId() {
		return id;
	}

	public OptLogDto setId(String id) {
		this.id = id;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public OptLogDto setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public OptLogDto setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getClientIp() {
		return clientIp;
	}

	public OptLogDto setClientIp(String clientIp) {
		this.clientIp = clientIp;
		return this;
	}

	public OptLogType getType() {
		return type;
	}

	public OptLogDto setType(OptLogType type) {
		this.type = type;
		return this;
	}

	public String getMethod() {
		return method;
	}

	public OptLogDto setMethod(String method) {
		this.method = method;
		return this;
	}

	public String getParams() {
		return params;
	}

	public OptLogDto setParams(String params) {
		this.params = params;
		return this;
	}

	public String getExceptionDetail() {
		return exceptionDetail;
	}

	public OptLogDto setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
		return this;
	}

	public String getBrowser() {
		return browser;
	}

	public OptLogDto setBrowser(String browser) {
		this.browser = browser;
		return this;
	}

	public String getOsInfo() {
		return osInfo;
	}

	public OptLogDto setOsInfo(String osInfo) {
		this.osInfo = osInfo;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OptLogDto)) return false;
		if (!super.equals(o)) return false;
		OptLogDto optLogDto = (OptLogDto) o;
		return Objects.equal(id, optLogDto.id) && Objects.equal(userId, optLogDto.userId) && Objects.equal(userName, optLogDto.userName) && Objects.equal(clientIp, optLogDto.clientIp) && type == optLogDto.type && Objects.equal(method, optLogDto.method) && Objects.equal(params, optLogDto.params) && Objects.equal(exceptionDetail, optLogDto.exceptionDetail) && Objects.equal(browser, optLogDto.browser) && Objects.equal(osInfo, optLogDto.osInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), id, userId, userName, clientIp, type, method, params, exceptionDetail, browser, osInfo);
	}
}