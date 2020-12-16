package com.madaoo.model.dto.base;

import com.madaoo.model.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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

}