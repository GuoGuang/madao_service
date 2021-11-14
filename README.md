<p align="center">
<a href="https://github.com/GuoGuang/madao" target="blank">
    <img src="https://yd-note.oss-cn-beijing.aliyuncs.com/favicon.ico" height="90" alt="madaoo.com logo" />
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

## [急速预览---直达](https://madaoo.com)
> 如果喜欢记得给个star哦🌟

更多内容请关注公众号：`码道人生`

![演示](http://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/images/demo.jpg)

本仓库基于spring、spring-boot、spring-cloud等全家桶模块搭建

## 一、技术选型
1. 核心框架：SpringBoot、SpringCloud全家桶、Python提供电影服务
2. 安全框架：Spring Security
3. 分布式任务调度：Elastic-job
4. 持久层框架：Jpa
5. 数据库连接池：Druid
6. 数据库/存储：Mysql8.0+、Redis
7. 消息队列：RabbitMQ
8. 日志管理：Logback
9. 三方服务： 邮件服务、阿里云短信服务、阿里云OSS对象存储
10. 运维/集成/部署：Jenkins、Docker...

## 二、前台项目请移步 
- [后台管理系统](https://github.com/GuoGuang/madao_admin_manage)
- [前台博客](https://github.com/GuoGuang/madao)

## 三、其他开源仓库
项目 | 地址
---|---
IDEA插件 （IDEA市场搜索 Generate Crud 安装） |  根据JPA实体类生成后端代码，简单快捷 👋
pinyinUtils | https://github.com/GuoGuang/pinyinUtils
后台管理框架 | https://github.com/GuoGuang/madao_admin_manage
数据爬虫集合 | https://github.com/GuoGuang/python-spider
前台博客 | https://github.com/GuoGuang/madao
如何写好单元测试 | https://github.com/GuoGuang/spring_junit_mockito_example


## 四、平台目录结构说明
```
├─madao-common-parent----------------------------父项目，公共依赖
│  │
│  ├─madao-common--------------------------------微服务公共包
│  │
│  ├─madao-service-config-------------------------微服务配置中心+微服务注册中心
│  │
│  ├─madao-service-api---------------------------微服务API工程
│  │
│  ├─madao-service-article-----------------------文章服务
│  │
│  ├─madao-service-base--------------------------基础服务
│  │
│  ├─madao-service-search------------------------搜索服务
│  │
│  ├─madao-service-user--------------------------RBAC用户服务
│  │
│  ├─madao-web-gateway---------------------------微服务网关中心

```
### 前置环境
1. IDE：IntelliJ IDEA
2. JDK：1.8+ 
3. 工具：Maven

## 五、快速开始
> 本项目需要你有一定得开发经验，对SpringCloud有基础的认识，此项目仅提供学习使用。

> 开源不易，如果此项目对您有帮助，麻烦点个star给作者一点动力，不胜感激。:sparkles:

### 1. 导入服务
![导入服务](https://github.com/GuoGuang/madao_service/blob/develop/madao-common-parent/image/service.png)
点击 "import module" 将服务一一导入，如果你嫌一个个导入麻烦，可以在madao-common-parent的pom.xml文件中最底下把<modules>标签放开，但是真正微服务开发一般一个团队或者一个人负责一个服务，没有一个人同时开发多个服务情况，毕竟是个人博客项目，导入方式可以自己定。
```
// install
mvn -B -DskipTests install -f madao-common-parent -Dmaven.test.skip=true  
或者 
idea里 maven选项选中madao-common-parent install 需要在Maven Projects界面里中选中"跳过测试"
```

### 2. 初始化数据库
导入系统根路径下madao.sql文件到数据库中

### 3. 初始化中间件
一个成熟的项目必然会依赖众多中间件，本项目也不例外，这里假设你会使用docker,如果你还没有接触到docker,那么可以参考我的另一篇文章[Docker入门](https://madaoo.com/article/1263480522076721152)

    使用docker启动mysql、redis
    ```
    // mysql
    docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql --lower_case_table_names=1
    
    // redis
    docker run --name myredis -d -p 6379:6379 -v /data/redis/redis.conf:/etc/redis/redis.conf -v /data/redis/data:/data redis  redis-server /etc/redis/redis.conf --requirepass "root" --appendonly yes
    
    // rebbitmq 暂时没有用到，可以先pass
    docker run -d --name rabbit-server -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin -p 5672:5672 -p 15672:15672 rabbitmq:3-management
    
    ```

### 4. 配置注册中心远程仓库
github fork此仓库[配置中心](https://github.com/GuoGuang/madao_config)
将里面的配置文件更改为你的地址，阿里云oss地址没有的话可以注释掉

### 5.  配置注册中心远程地址
在madao-server-config服务中找到bootstrap.yml文件，配置如下

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

# 非对称加密，将下面的配置注释。如果上面配置的"你的github密码"地方是明文，这里就不用管
# encrypt:
#   key-store:
#     location: classpath:xxx
#     alias: xxx
#     password: xxx
#     secret: xxx
```

### 6. 启动
第一步首先启动madao-service-config服务，这个是注册中心+配置中心，然后再启动其他的服务。


## Swagger
[本地访问地址](http://127.0.0.1:8080/swagger-ui/)
默认账号：admin 密码：password

### SpringSecurity流程
大致了解一下SpringSecurity的流程，方便对整个项目的了解
![Security时序图](https://yd-note.oss-cn-beijing.aliyuncs.com/spring/Oauth%26SpringSecurity/SpringSecurity%20%E8%AE%A4%E8%AF%81%E6%B5%81%E7%A8%8B%E6%97%B6%E5%BA%8F%E5%9B%BE.png)


## 常见问题

### 1、关于内存问题
SpringCloud是比较吃内存的，如果你不指定内存大小，8G内存一般启3、4个就满了，所以这里需要配置下每个服务内存大小

打开Environment config,如果你不指定在哪打开，参考https://www.jetbrains.com/help/idea/2019.3/run-debug-configuration-junit.html?utm_campaign=IU&utm_content=2019.3&utm_medium=link&utm_source=product#configTab 的VM options

类似 config、gateway、eureka、 Oauth2Authentication、服务配置为 -Xmx128m -Xms128m -Xmn50m 

其他的-Xmx236m -Xms236m -Xmn150m 

以上配置请不要再生产使用，仅作为本地开发调试，为解决内存不足问题的，当然如果你的内存够大，可以忽略以上配置

### 2、javax.annotation.processing.FilerException: Attempt to recreate a file for xxx
maven install之前先maven clean


## 启动微服务
### 架构图
![架构图](https://github.com/GuoGuang/madao_service/blob/develop/madao-common-parent/image/%E6%9E%B6%E6%9E%84%E5%9B%BE1.png)
![架构图](https://github.com/GuoGuang/madao_service/blob/develop/madao-common-parent/image/%E6%9E%B6%E6%9E%84%E5%9B%BE2.png)

### 服务监控平台
启动 madao-service-monitor 服务 
访问 [地址](http://127.0.0.1:9002)
![图1](https://github.com/GuoGuang/madao_service/blob/develop/madao-common-parent/image/Application.png)
![图2](https://github.com/GuoGuang/madao_service/blob/develop/madao-common-parent/image/Wallboard.png)
![图3](https://github.com/GuoGuang/madao_service/blob/develop/madao-common-parent/image/Details.png)

## 更新日志
### [20210204]
Spring-Cloud-Gateway 集成 Swagger,支持认证后访问，由网关统一管理

### [20210412]
移除不必要得依赖，项目结构重构

### [20210505]
将资源缓存化，加快访问速度