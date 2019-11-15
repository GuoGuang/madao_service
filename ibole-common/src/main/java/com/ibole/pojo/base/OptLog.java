package com.ibole.pojo.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ibole.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 *  登录日志
 **/
@Getter
@Setter
public class OptLog extends BasePojo implements Serializable {
    /**
     * 日志表
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 操作人
     */
    private String userId;
	@TableField(exist = false)
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