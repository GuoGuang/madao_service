package com.youyd.pojo.base;

import com.youyd.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 *  登录日志
 * @author LGG
 * @create 2018-09-27
 **/
@Getter
@Setter
public class OptLog extends BasePojo implements Serializable {
    /**
     * 日志表
     */
    private String id;

    /**
     * 操作人
     */
    private String userId;

    /**
     * 操作ip
     */
    private String clientIp;

    /**
     * 操作类型（1：增，2：删，3：改）
     */
    private Integer type;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统信息
     */
    private String osinfo;

}