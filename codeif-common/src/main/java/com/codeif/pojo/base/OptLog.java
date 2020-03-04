package com.codeif.pojo.base;

import com.codeif.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ba_opt_log")
public class OptLog extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeif.config.IdGeneratorConfig")
    @ApiModelProperty("操作日志表主键")
    @Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
    private String id;

    @ApiModelProperty("操作人id")
    @Column(length = 20)
    private String userId;

    @ApiModelProperty("操作人名称")
    @Transient
    private String userName;

    @ApiModelProperty("操作ip")
    @Column(length = 20)
    private String clientIp;

    @ApiModelProperty(value = "操作类型（1：增，2：删，3：改）",example = "1")
    @Column(length = 1)
    private Integer type;

    @ApiModelProperty("操作方法名称")
    @Column(length = 100)
    private String method;

    @ApiModelProperty("操作方法的参数（json）")
    @Column(length = 500)
    private String params;

    @ApiModelProperty("异常详情")
    @Column(length = 500)
    private String exceptionDetail;

    @ApiModelProperty("浏览器")
    @Column(length = 50)
    private String browser;

    @ApiModelProperty("操作系统信息")
    @Column(length = 50)
    private String osInfo;

}