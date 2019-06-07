# Spring-Excloud

![springboot](https://img.shields.io/badge/springboot-2.0.2.RELEASE-brightgreen.svg)    ![springCloud](https://img.shields.io/badge/springcloud-Finchley.SR2-brightgreen.svg)     ![jdk](https://img.shields.io/badge/jdk->=1.8-blue.svg)    ![license](https://img.shields.io/github/license/mashape/apistatus.svg)

本仓库基于spring、spring-boot、spring-cloud等全家桶模块搭建

## 技术选型
1. 核心框架：SpringBoot、SpringCloud 全家桶
2. 安全框架：Spring Security
3. 分布式任务调度：Elastic-job
4. 持久层框架：MyBatis、Mybatis_Plus
5. 数据库连接池：Alibaba Druid
6. 数据库/存储：Mysql8.0+、Redis
7. 消息队列：RabbitMQ
8. 日志管理：Logback
9. 三方服务： 邮件服务、阿里云短信服务、阿里云OSS对象存储
10. 运维/集成/部署：Jenkins、Docker...

## 平台目录结构说明
```
├─youyd-common-parent----------------------------父项目，公共依赖
│  │
│  ├─youyd-common--------------------------------微服务公共包
│  │
│  ├─youyd-common-redis--------------------------工具-Redis封装包
│  │
│  ├─youyd-database-mybatis----------------------数据库
│  │
│  ├─youyd-server-config-------------------------微服务配置中心
│  │
│  ├─youyd-server-eureka-------------------------微服务注册中心
│  │
│  ├─youyd-server-monitor-----------------------—微服务监控中心 
│  │
│  ├─youyd-service-api---------------------------微服务API工程
│  │
│  ├─youyd-service-article-----------------------文章服务
│  │
│  ├─youyd-service-base--------------------------基础微服务
│  │
│  ├─youyd-service-search------------------------搜索微服务
│  │
│  ├─youyd-service-tweets------------------------推特微服务
│  │
│  ├─youyd-service-user--------------------------用户微服务
│  │
│  ├─youyd-web-gateway---------------------------微服务网关中心

```


## 架构图
![架构图](https://github.com/GuoGuang0536/youyd_springcloud_service/blob/master/youyd-common-parent/image/%E6%9E%B6%E6%9E%84%E5%9B%BE.jpg)

## 服务监控平台
![图1](https://github.com/GuoGuang0536/youyd_springcloud_service/blob/develop/youyd-common-parent/image/Application.png)
![图2](https://github.com/GuoGuang0536/youyd_springcloud_service/blob/develop/youyd-common-parent/image/Wallboard.png)
![图3](https://github.com/GuoGuang0536/youyd_springcloud_service/blob/develop/youyd-common-parent/image/Details.png)