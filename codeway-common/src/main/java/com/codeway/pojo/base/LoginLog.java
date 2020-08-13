package com.codeway.pojo.base;

import com.codeway.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
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
    @GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
    @ApiModelProperty("登录日志表主键")
    @Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
    private String id;

    @ApiModelProperty("登录人")
    @Column(length = 20)
    private String userId;

    @Transient
    @ApiModelProperty("登录人名称")
    @Column(length = 20)
    private String userName;

    @ApiModelProperty("登录ip")
    @Column(length = 20)
    private String clientIp;

    @ApiModelProperty("浏览器")
    @Column(length = 50)
    private String browser;

    @ApiModelProperty("操作系统信息")
    @Column(length = 100)
    private String osInfo;

}