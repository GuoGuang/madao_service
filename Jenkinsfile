#!groovy
pipeline {
    agent any
    parameters {
        string(name: 'repoUrl', defaultValue: 'https://github.com/GuoGuang/madao_service.git', description: 'git代码路径')
        string(name: 'repoBranch', defaultValue: 'dev', description: 'git分支名称')
        string(name: 'pomPath', defaultValue: 'pom.xml', description: 'pom.xml的相对路径')
        string(name: 'warLocation', defaultValue: 'rpc/war/target/*.war', description: 'war包的相对路径 ')
        choice(name: 'server', choices: '192.168.1.107,9090,*****,*****\n192.168.1.60,9090,*****,*****', description: '测试服务器列表选择(IP,JettyPort,Name,Passwd)')
        choice(name: 'project', choices: [
                'madao-server-config:9009',
                'madao-service-user:9007:9099',
                'madao-web-gateway:8080',
                'madao-service-base:9008',
                'madao-service-article:9003',
                'madao-authentication-server:8090',
        ], description: '选择微服务')
        string(name: 'lineCoverage', defaultValue: '20', description: '单元测试代码覆盖率要求(%)，小于此值pipeline将会失败！')
        booleanParam(name: 'isCommitQA', description: '是否邮件通知测试人员进行人工验收', defaultValue: false)
    }
    tools {
      maven 'maven3'
    }
    environment {
        FRESH_HOST = "registry.cn-hongkong.aliyuncs.com"
        REMOTE_SCRIPT = 'sshpass -f /var/jenkins_home/password.txt ssh -t -t -o StrictHostKeyChecking=no root@${INSTANCE_IP}'
        REMOTE_IP = "82.156.148.211"
        DOCKER_IMAGE = "${params.project}"
        DOCKER_CONTAINER = "${params.project}"
        QA_EMAIL = "1831682775@qq.com"
        BUILD_USER_EMAIL = "1831682775@qq.com"
        BUILD_USER  = "构建人"
        ITEST_JOBNAME = "InterfaceTest_ExpertPatient"
        CRED_ID="*****-****-****-****-*********"
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }
    triggers {
        pollSCM('H 4 * * 1-5')
    }
    stages {
        stage('代码获取') {
            steps {
                script {
                    def split = params.project.split(":")
                    serviceName = split[0]
                    servicePort = split[1]
                    echo "is-------${params.project}"
                   if("${serviceName}" == "madao-service-user"){
                        secondPort = split[2]
                    }
                }
                echo "开始从 ${params.repoUrl} 获取代码......"
                sh "rm -rf ./*"
                sh "git clone -b dev --depth=1 https://github.com/GuoGuang/madao_service.git"
            }
        }

        stage("Maven构建") {
            steps {
                echo "构建--->${serviceName}"
                sh "pwd"
                sh "/bin/cp -f /var/jenkins_home/service-config/config-server.jks madao_service/madao-server-config/src/main/resources/"
                sh "/bin/cp -f /var/jenkins_home/service-config/bootstrap.yml madao_service/madao-server-config/src/main/resources/"
                sh "/bin/cp -f /var/jenkins_home/service-config/application.yml madao_service/madao-authentication-server/src/main/resources/"
                sh "/bin/cp -f /var/jenkins_home/service-config/JWT.keystore madao_service/madao-authentication-server/src/main/resources/"
                sh "mvn -B -DskipTests install -f madao_service/madao-common-parent"
                sh "mvn -B -DskipTests install -f madao_service/madao-common"
                sh "mvn -B -DskipTests install -f madao_service/madao-service-api"
                sh "mvn -B -DskipTests install -f madao_service/${serviceName}"
                echo '-->> -->>maven打包构建完成!'
            }
        }
        stage("Docker构建") {
            steps {
                script {
                    def container = sh(returnStdout: true, script: "docker ps -a | grep $serviceName | awk '{print \$1}'").trim()
                    if (container.size() > 0) {
                        sh "docker ps -a | grep $serviceName | awk  '{print \$1}' | xargs docker stop"
                        sh "docker ps -a | grep $serviceName | awk '{print \$1}' | xargs docker rm"
                        echo '-->> 1#停止并删除容器 -->>'
                    }
                    def image = sh(returnStdout: true, script: "docker images | grep $serviceName | awk '{print \$3}'").trim()
                    if (image.size() > 0) {
                        sh "docker images | grep $serviceName | awk '{print \$3}' | xargs docker rmi -f"
                        echo '-->> 2#停止并删除镜像 -->>'
                    }
                }
                sh "pwd"
               echo "/${WORKSPACE}"
                dir(path: "/${WORKSPACE}/madao_service/${serviceName}") {
                    sh "pwd"
                    sh "docker build -t ${serviceName}:${env.BUILD_ID} ."
                    script {
                            sh "docker login --username=1831682775@qq.com --password ${DOCKER_HUB_PASSWORD} registry.cn-beijing.aliyuncs.com"
                            sh "docker tag ${serviceName}:${env.BUILD_ID} registry.cn-beijing.aliyuncs.com/madaoo/${serviceName}:${env.BUILD_ID}"
                            sh "docker push registry.cn-beijing.aliyuncs.com/madaoo/${serviceName}:${env.BUILD_ID}"
                            echo "构建并推送到远程服务器成功--->"
                    }
                }
            }
        }

        stage('单元测试') {
            steps {
              echo "starting unitTest......"
            }
        }
        stage('静态检查') {
            steps {
                echo "starting codeAnalyze with SonarQube......"
            }
        }

        stage('部署正式环境') {
            steps {
                echo "开始部署到----> ${serviceName}......"
                script {
                    echo "即将进入"
                                               // jenkinsci/blueocean镜像是基于Alpine Linux系统
                        // sh "sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories"
                        // sh "apk update"
                        // sh "apk add sshpass"

                        // jenkins/jenkins镜像是基于Ubuntu系统
                        sh "sed -i s@/archive.ubuntu.com/@/mirrors.aliyun.com/@g /etc/apt/sources.list"
                        sh "apt-get update"
                        sh "apt-get install sshpass"
                    
                        def container = sh(returnStdout: true, script: "${REMOTE_SCRIPT} docker ps -a | grep $serviceName | awk '{print \$1}'").trim()
                        if (container.size() > 0) {
                            sh "${REMOTE_SCRIPT} docker ps -a | grep $serviceName | awk  '{print \$1}' | xargs ${REMOTE_SCRIPT} docker stop"
                            sh "${REMOTE_SCRIPT} docker ps -a | grep $serviceName | awk '{print \$1}' | xargs ${REMOTE_SCRIPT} docker rm"
                            echo '-->> 1#停止并删除远程服务器容器 -->>'
                        }
                        def image = sh(returnStdout: true, script: "${REMOTE_SCRIPT} docker images | grep $serviceName | awk '{print \$3}'").trim()
                        if (image.size() > 0) {
                            sh "${REMOTE_SCRIPT} docker images | grep $serviceName | awk '{print \$3}' | xargs ${REMOTE_SCRIPT} docker rmi -f"
                            echo '-->> 2#停止并删除远程服务器镜像 -->>'
                        }
                        sh "pwd"
                        sh "${REMOTE_SCRIPT} pwd "
                        sh "${REMOTE_SCRIPT} docker -v "
                        sh "${REMOTE_SCRIPT} docker login --username=1831682775@qq.com --password ${DOCKER_HUB_PASSWORD} registry.cn-beijing.aliyuncs.com"
                        sh "${REMOTE_SCRIPT} docker pull registry.cn-beijing.aliyuncs.com/madaoo/${serviceName}:${env.BUILD_ID}"
                          if("${serviceName}" == "madao-service-user"){
                                sh "${REMOTE_SCRIPT} docker run -p ${servicePort}:${servicePort} -p ${secondPort}:${secondPort} --name ${serviceName} -d registry.cn-beijing.aliyuncs.com/madaoo/${serviceName}:${env.BUILD_ID}"
                           }else{
                                sh "${REMOTE_SCRIPT} docker run -p ${servicePort}:${servicePort} --name ${serviceName} -d registry.cn-beijing.aliyuncs.com/madaoo/${serviceName}:${env.BUILD_ID}"
                            }
                        echo '-->> #远程主机构建成功-->>'
                }
            }
        }

        stage('接口自动化测试') {
            steps{
                echo "starting interfaceTest......"
            }
        }

        stage('性能自动化测试 ') {
            steps {
                echo "starting performanceTest......"
            }
        }
         stage('SUCCESS') {
             steps{
                 echo "done......"
             }
         }
    }
}
