package com.madao.model.dto.base;

import com.madao.enums.OptLogType;
import com.madao.model.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OptLogDto extends BasePojo implements Serializable {

	@ApiModelProperty("操作日志表主键")
	private String id;

	@ApiModelProperty("操作人id")
	private String userId;

	@ApiModelProperty("操作人名称")
	private String userName;

	@ApiModelProperty("操作ip")
	private String clientIp;

	@ApiModelProperty(value = "操作类型（1：增，2：删，3：改）", example = "1")
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

}