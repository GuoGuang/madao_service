package com.ibole.pojo.base;

import com.ibole.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 登录日志
 **/
@Getter
@Setter
@Entity
@Table(name = "ba_opt_log")
public class OptLog extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
    private String id;

    /**
     * 操作人
     */
    private String userId;

    @Transient
    private String userName;
    /**
     * 操作ip
     */
    private String clientIp;

    /**
     * 操作类型（1：增，2：删，3：改）
     */
    private Integer type;

    /**
     * 操作方法名称
     */
    private String method;

    /**
     * 操作方法的参数（json）
     */
    private String params;

    /**
     * 异常详情
     */
    private String exceptionDetail;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统信息
     */
    private String osInfo;

}