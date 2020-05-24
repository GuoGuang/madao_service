
<p align="center">
<a href="https://github.com/GuoGuang/codeway" target="blank">
    <img src="https://yd-note.oss-cn-beijing.aliyuncs.com/favicon.ico" height="90" alt="codeway.me logo" />
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
- [后台管理系统](https://github.com/GuoGuang/codeway_admin_manage)
- [前台博客](https://github.com/GuoGuang/codeway)

## 平台目录结构说明
```
├─codeway-common-parent----------------------------父项目，公共依赖
│  │
│  ├─codeway-common--------------------------------微服务公共包
│  │
│  ├─codeway-common-db-----------------------------数据库
│  │
│  ├─codeway-service-config-------------------------微服务配置中心
│  │
│  ├─codeway-service-eureka-------------------------微服务注册中心
│  │
│  ├─codeway-service-monitor-----------------------—微服务监控中心 
│  │
│  ├─codeway-service-api---------------------------微服务API工程
│  │
│  ├─codeway-service-article-----------------------文章服务
│  │
│  ├─codeway-service-base--------------------------基础服务
│  │
│  ├─codeway-service-search------------------------搜索服务
│  │
│  ├─codeway-service-user--------------------------用户服务
│  │
│  ├─codeway-web-gateway---------------------------微服务网关中心

```

## 快速开始
> 本项目需要你有一定的开发经验，对SpringCloud有基础的认识，此项目仅提供学习使用，新手不建议使用。

> 开源不易，如果此项目对您有帮助，麻烦点个star给作者一点动力，不胜感激。:sparkles:

0. 导入服务
![导入服务](https://github.com/GuoGuang/codeway_service/blob/develop/codeway-common-parent/image/service.png)
点击 "import module" 将服务一一导入，如果你嫌一个个导入麻烦，可以在codeway-common-parent的pom.xml文件中最底下把<modules>标签放开，但是真正微服务开发一般一个团队或者一个人负责一个服务，没有一个人同时开发多个服务情况，毕竟是个人博客项目，导入方式可以自己定。

1. 一个成熟的项目必然会依赖众多中间件，本项目也不例外，这里假设你会使用docker,如果你还没有接触到docker,那么可以参考我的另一篇文章[Docker入门](https://codeway.me/article/1263480522076721152)

使用docker启动mysql、redis
```
// mysql
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql --lower_case_table_names=1

// redis
docker run --name myredis -d -p 6379:6379 -v /data/redis/redis.conf:/etc/redis/redis.conf -v /data/redis/data:/data redis  redis-server /etc/redis/redis.conf --requirepass "root" --appendonly yes

// rebbitmq 暂时没有用到，可以先pass
docker run -d --name rabbit-server -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin -p 5672:5672 -p 15672:15672 rabbitmq:3-management

```

2 . 配置注册中心远程仓库
github fork此仓库 [配置中心](https://github.com/GuoGuang/codeway_config)

将里面的配置文件更改为你的地址，阿里云oss地址没有的话可以注释掉

3. 配置注册中心远程地址
在codeway-server-config服务中找到bootstrap.yml文件，配置如下
```
spring:
  profiles:
    active: ${ACTIVE_PROFILE:dev}
  cloud:
    config:
      server:
        encrypt:
          enabled: true # 启用config加密功能
        git:
          uri: 第2步配置的注册中心远程仓库地址
          searchPaths: ${spring.profiles.active}
          default-label: master
          username: 你的github账号
          password: '你的github密码'

      enabled: true    # 开启消息跟踪

# 非对称加密，将下面的配置注释
# encrypt:
#   key-store:
#     location: classpath:xxx
#     alias: xxx
#     password: xxx
#     secret: xxx
```

4. 关于内存问题

SpringCloud是比较吃内存的，如果你不指定内存大小，8G内存一般启3、4个就满了，所以这里需要配置下每个服务内存大小

打开Environment config,如果你不指定在哪打开，参考https://www.jetbrains.com/help/idea/2019.3/run-debug-configuration-junit.html?utm_campaign=IU&utm_content=2019.3&utm_medium=link&utm_source=product#configTab 的VM options

类似 config、gateway、eureka、 Oauth2Authentication、服务配置为 -Xmx128m -Xms128m -Xmn50m 

其他的-Xmx236m -Xms236m -Xmn150m 

以上配置请不要再生产使用，仅作为本地开发调试，为解决内存不足问题的，当然如果你的内存够大，可以忽略以上配置

4. 启动微服务
## 架构图
![架构图](https://github.com/GuoGuang/codeway_service/blob/develop/codeway-common-parent/image/%E6%9E%B6%E6%9E%84%E5%9B%BE1.png)
![架构图](https://github.com/GuoGuang/codeway_service/blob/develop/codeway-common-parent/image/%E6%9E%B6%E6%9E%84%E5%9B%BE2.png)

## 服务监控平台
启动 codeway-service-monitor 服务 
访问 [地址](http://127.0.0.1:9002)
![图1](https://github.com/GuoGuang/codeway_service/blob/develop/codeway-common-parent/image/Application.png)
![图2](https://github.com/GuoGuang/codeway_service/blob/develop/codeway-common-parent/image/Wallboard.png)
![图3](https://github.com/GuoGuang/codeway_service/blob/develop/codeway-common-parent/image/Details.png)
