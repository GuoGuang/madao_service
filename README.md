
<p align="center">
<a href="https://github.com/GuoGuang/codeif" target="blank">
    <img src="https://yd-note.oss-cn-beijing.aliyuncs.com/favicon.ico" height="90" alt="codeif.me logo" />
</a>
</p>

<p align="center">
  <a href="#">
    <img src="https://img.shields.io/badge/jdk->=1.8-blue.svg" alt="vue">
  </a>
  <a href="#">
    <img src="https://img.shields.io/badge/license-GPL%20(%3E%3D%202)-blue" alt="vue">
  </a>
  <a href="#">
    <img src="https://img.shields.io/badge/springcloud-Finchley.SR2-brightgreen.svg" alt="element-ui">
  </a>
  <a href="#">
    <img src="https://img.shields.io/badge/springboot-2.0.2.RELEASE-brightgreen.svg" alt="license">
  </a>
</p>


本仓库基于spring、spring-boot、spring-cloud等全家桶模块搭建

## 技术选型
1. 核心框架：SpringBoot、SpringCloud 全家桶
2. 安全框架：Spring Security
3. 分布式任务调度：Elastic-job
4. 持久层框架：Jpa
5. 数据库连接池：Druid
6. 数据库/存储：Mysql8.0+、Redis
7. 消息队列：RabbitMQ
8. 日志管理：Logback
9. 三方服务： 邮件服务、阿里云短信服务、阿里云OSS对象存储
10. 运维/集成/部署：Jenkins、Docker...

## 前台项目请移步 
  -  (后台管理系统)[https://github.com/GuoGuang/codeif_admin_manage]
  -  (前台博客)[https://github.com/GuoGuang/codeif]

## 平台目录结构说明
```
├─youyd-common-parent----------------------------父项目，公共依赖
│  │
│  ├─youyd-common--------------------------------微服务公共包
│  │
│  ├─youyd-common-db-----------------------------数据库
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
│  ├─youyd-service-base--------------------------基础服务
│  │
│  ├─youyd-service-search------------------------搜索服务
│  │
│  ├─youyd-service-user--------------------------用户服务
│  │
│  ├─youyd-web-gateway---------------------------微服务网关中心

```

## 快速开始
1. 使用docker启动mysql、redis、rabbitmq 
```

// mysql
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql --lower_case_table_names=1

// redis
docker run --name myredis -d -p 6379:6379 -v /data/redis/redis.conf:/etc/redis/redis.conf -v /data/redis/data:/data redis  redis-server /etc/redis/redis.conf --requirepass "root" --appendonly yes

// rebbitmq 

docker run -d --name rabbit-server -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin -p 5672:5672 -p 15672:15672 rabbitmq:3-management

```

2 . 修改注册中心配置文件

github clone 此工程 [配置中心](https://github.com/GuoGuang0536/youyd_springcloud_config)

将里面的配置文件更改为你的地址，阿里云oss地址没有的话可以注释掉

3 . 启动微服务

## 架构图
![架构图](https://github.com/GuoGuang/codeif_service/blob/develop/codeif-common-parent/image/%E6%9E%B6%E6%9E%84%E5%9B%BE1.png)
![架构图](https://github.com/GuoGuang/codeif_service/blob/develop/codeif-common-parent/image/%E6%9E%B6%E6%9E%84%E5%9B%BE2.png)

## 服务监控平台
启动 youyd-server-monitor 服务 
访问 [地址](http://127.0.0.1:9002)
![图1](https://github.com/GuoGuang/codeif_service/blob/develop/codeif-common-parent/image/Application.png)
![图2](https://github.com/GuoGuang/codeif_service/blob/develop/codeif-common-parent/image/Wallboard.png)
![图3](https://github.com/GuoGuang/codeif_service/blob/develop/codeif-common-parent/image/Details.png)
