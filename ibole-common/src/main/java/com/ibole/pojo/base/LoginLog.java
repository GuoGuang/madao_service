package com.ibole.pojo.base;

import com.ibole.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


/**
 *  登录日志
 **/
@Getter
@Setter
@Entity
@Table(name = "ba_login_log")
public class LoginLog extends BasePojo implements Serializable {
    /**
     * 日志表
     */
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
    private String id;

    /**
     * 登录人
     */
    private String userId;

    @Transient
    private String userName;

    /**
     * 登录ip
     */
    private String clientIp;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统信息
     */
    private String osInfo;

}