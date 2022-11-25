package com.madao.model.dto.base;

import com.google.common.base.Objects;
import com.madao.enums.OptLogType;
import com.madao.model.BasePojo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OptLogDto extends BasePojo implements Serializable {

	@Schema(title = "操作日志表主键")
	private String id;

	@Schema(title = "操作人id")
	private String userId;

	@Schema(title = "操作人名称")
	private String userName;

	@Schema(title = "操作ip")
	private String clientIp;

	@Schema(title = "操作类型（1：增，2：删，3：改）")
	private OptLogType type;

	@Schema(title = "操作方法名称")
	private String method;

	@Schema(title = "操作方法的参数（json）")
	private String params;

	@Schema(title = "异常详情")
	private String exceptionDetail;

	@Schema(title = "浏览器")
	private String browser;

	@Schema(title = "操作系统信息")
	private String osInfo;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OptLogDto optLogDto)) return false;
		if (!super.equals(o)) return false;
		return Objects.equal(id, optLogDto.id) && Objects.equal(userId, optLogDto.userId) && Objects.equal(userName, optLogDto.userName) && Objects.equal(clientIp, optLogDto.clientIp) && type == optLogDto.type && Objects.equal(method, optLogDto.method) && Objects.equal(params, optLogDto.params) && Objects.equal(exceptionDetail, optLogDto.exceptionDetail) && Objects.equal(browser, optLogDto.browser) && Objects.equal(osInfo, optLogDto.osInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), id, userId, userName, clientIp, type, method, params, exceptionDetail, browser, osInfo);
	}
}