package com.madao.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 监控MQ消息数量的实体
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-02-10 18:27
 */
@Entity
@Table(name = "mq_monitor",
        uniqueConstraints=@UniqueConstraint(columnNames={"queue"}))
@org.hibernate.annotations.Table(appliesTo = "mq_monitor",comment="MQ队列数量监视器")
@Getter
@Setter
public class MqMonitor {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
    private String id;

    @Column(columnDefinition="varchar(100) COMMENT '队列名称'",nullable = false)
    private String queue;

    @Column(columnDefinition="varchar(300) COMMENT '队列描述' default ''")
    private String queueDesc;

    @Column(columnDefinition="int COMMENT '阈值' default null")
    private int threshold;

    @Column(columnDefinition="varchar(500) COMMENT '邮件收件人'",nullable = false)
    private String receiver;

    @Column(columnDefinition="varchar(500) COMMENT '短信收件人'",nullable = false)
    private String mobile;

}
