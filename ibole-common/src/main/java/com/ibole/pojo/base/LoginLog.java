package com.ibole.pojo.base;

import com.ibole.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ba_login_log")
public class LoginLog extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
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