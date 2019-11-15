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
 * @author LGG
 * @create 2018-09-27
 **/
@Getter
@Setter
public class LoginLog extends BasePojo implements Serializable {
    /**
     * 日志表
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 登录人
     */
    private String userId;
    @TableField(exist = false)
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