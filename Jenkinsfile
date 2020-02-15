#!groovy
pipeline {
    //在任何可用的代理上执行Pipeline
    agent any
    //参数化变量，目前只支持[booleanParam, choice, credentials, file, text, password, run, string]这几种参数类型，其他高级参数化类型还需等待社区支持。
    parameters {
        //git代码路径【参数值对外隐藏】
        string(name: 'repoUrl', defaultValue: 'https://github.com/GuoGuang/ibole_service.git', description: 'git代码路径')
        //repoBranch参数后续替换成git parameter不再依赖手工输入,JENKINS-46451【git parameters目前还不支持pipeline】
        string(name: 'repoBranch', defaultValue: 'develop', description: 'git分支名称')
        //pom.xml的相对路径
        string(name: 'pomPath', defaultValue: 'pom.xml', description: 'pom.xml的相对路径')
        //war包的相对路径
        string(name: 'warLocation', defaultValue: 'rpc/war/target/*.war', description: 'war包的相对路径 ')
        //服务器参数采用了组合方式，避免多次选择，使用docker为更佳实践【参数值对外隐藏】
        choice(name: 'server', choices: '192.168.1.107,9090,*****,*****\n192.168.1.60,9090,*****,*****', description: '测试服务器列表选择(IP,JettyPort,Name,Passwd)')
        choice(name: 'project', choices: [
                'ibole-server-config:9009',
                'ibole-server-eureka:5000',
                'ibole-service-user:9007',
                'ibole-web-gateway:8080',
                'ibole-service-base:9008',
                'ibole-service-article:9003',
                'ibole-authorization-server:8091',
                'ibole-authentication-server:8090',

        ], description: '选择微服务')
        //测试服务器的dubbo服务端口
        string(name: 'dubboPort', defaultValue: '31100', description: '测试服务器的dubbo服务端口')
        //单元测试代码覆盖率要求，各项目视要求调整参数
        string(name: 'lineCoverage', defaultValue: '20', description: '单元测试代码覆盖率要求(%)，小于此值pipeline将会失败！')
        //若勾选在pipelie完成后会邮件通知测试人员进行验收
        booleanParam(name: 'isCommitQA', description: '是否邮件通知测试人员进行人工验收', defaultValue: false)
    }
    //环境变量，初始确定后一般不需更改
//     tools {
      //  maven 'maven3'
    // }
    //常量参数，初始确定后一般不需更改
    environment {

        // 阿里云docker仓库凭证 ：这是jenkins管理界面中定义的凭证名称为“aliyun-docker”
//        FRESH_CREDS = credentials('aliyun-docker')
//        BUILD_NUMBER = credentials('aliyun-docker')
        // 仓库docker 地址、镜像名、容器名称
        FRESH_HOST = "registry.cn-hongkong.aliyuncs.com"
        REMOTE_IP = "207.148.76.64"
        DOCKER_IMAGE = "${params.project}"
        DOCKER_CONTAINER = "${params.project}"
        //测试人员邮箱地址【参数值对外隐藏】
        QA_EMAIL = "1831682775@qq.com"
        BUILD_USER_EMAIL = "1831682775@qq.com"
        BUILD_USER  = "构建人"
        //接口测试（网络层）的job名，一般由测试人员编写
        ITEST_JOBNAME = "InterfaceTest_ExpertPatient"
        //git服务全系统只读账号cred_id【参数值对外隐藏】
        CRED_ID="*****-****-****-****-*********"
    }
    options {
        //保持构建的最大个数
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }
    //定期检查开发代码更新，工作日每晚4点做daily build
    triggers {
        pollSCM('H 4 * * 1-5')
    }
    //pipeline运行结果通知给触发者
/*    post {
        success {
            script {
                wrap([$class: 'BuildUser']) {
                    mail to: "${BUILD_USER_EMAIL}",
                            subject: "PineLine '${JOB_NAME}' (${BUILD_NUMBER}) result",
                            body: "${BUILD_USER}'s pineline '${JOB_NAME}' (${BUILD_NUMBER}) run success\n请及时前往${env.BUILD_URL}进行查看"
                }
            }
        }
        failure {
            script {
                wrap([$class: 'BuildUser']) {
                    mail to: "${BUILD_USER_EMAIL}",
                            subject: "PineLine '${JOB_NAME}' (${BUILD_NUMBER}) result",
                            body: "${BUILD_USER}'s pineline  '${JOB_NAME}' (${BUILD_NUMBER}) run failure\n请及时前往${env.BUILD_URL}进行查看"
                }
            }
        }
        unstable {
            script {
                wrap([$class: 'BuildUser']) {
                    mail to: "${BUILD_USER_EMAIL}",
                            subject: "PineLine '${JOB_NAME}' (${BUILD_NUMBER})结果",
                            body: "${BUILD_USER}'s pineline '${JOB_NAME}' (${BUILD_NUMBER}) run unstable\n请及时前往${env.BUILD_URL}进行查看"
                }
            }
        }
    }*/

    //pipeline的各个阶段场景
    stages {

        stage('代码获取') {
            steps {
                //根据param.server分割获取参数,包括IP,jettyPort,username,password
                script {
                    //      def split = params.server.split(",")
                    //		serverIP = split[0]
                    //		jettyPort = split[1]
                    //		serverName = split[2]
                    //		serverPasswd = split[3]
                    
                    def split = params.project.split(":")
                    serviceName = split[0]
                    servicePort = split[1]
                    
                }
                echo "开始从 ${params.repoUrl} 获取代码......"
                // Get some code from a GitHub repository
                // git credentialsId: CRED_ID, url: params.repoUrl, branch: params.repoBranch
                sh "rm -rf ./*"
                // 只获取最近一次提交的
                sh "git clone -b develop https://github.com/GuoGuang/ibole_service.git "
            }
        }

        stage("Maven构建") {
            agent {
                docker {
                    image 'maven:3-alpine' 
                    args '-v /root/.m2:/root/.m2'  //持载到本地，减少重复下载量，使用ali源
                }
            }
            // maven打包命令
            steps {
                echo "构建--->${serviceName}"
                sh "pwd"
                sh "/bin/cp /var/jenkins_home/service-config/config-server.jks ibole-server-config/src/main/resources/"
                sh "/bin/cp /var/jenkins_home/service-config/bootstrap.yml ibole-server-config/src/main/resources/"
                sh "mvn -B -DskipTests install -f ibole-common"
                sh "mvn -B -DskipTests install -f ibole-common-db"
                sh "mvn -B -DskipTests install -f ibole-service-api"
                sh "mvn -B -DskipTests install -f ${serviceName}"
                echo '-->> -->>maven打包构建完成!'

            }
        }

        // dockerfile构建镜像 -- 推送到远程仓库
        stage("Docker构建") {
            steps {
                script {
                    // 停止并删除列表中有 ${DOCKER_CONTAINER} 的容器
                    def container = sh(returnStdout: true, script: "docker ps -a | grep $serviceName | awk '{print \$1}'").trim()
                    if (container.size() > 0) {
                        sh "docker ps -a | grep $serviceName | awk  '{print \$1}' | xargs docker stop"
                        sh "docker ps -a | grep $serviceName | awk '{print \$1}' | xargs docker rm"
                        echo '-->> 1#停止并删除容器 -->>'
                    }
                    // 删除列表中有 ${DOCKER_IMAGE} 的镜像
                    def image = sh(returnStdout: true, script: "docker images | grep $serviceName | awk '{print \$3}'").trim()
                    if (image.size() > 0) {
                        sh "docker images | grep $serviceName | awk '{print \$3}' | xargs docker rmi -f"
                        echo '-->> 2#停止并删除镜像 -->>'
                    }
                }
                sh "pwd"
                // 切换到某目录下执行，执行完steps会回退到原来所在目录
                // 直接的构建是在容器里，这个是在 Jenkins 容器里，所以空间不一样 容器的空间是原空间路径后面多了 @2
                // 或者说在 Maven构建 步骤把 'cd ${WORKSPACE}/ibole-server-eureka' 替换为'cd ${WORKSPACE}@2/ibole-server-eureka'
                // dir(path: "../ibole_service_develop@2/${params.project}") {
                dir(path: "ibole_service/${serviceName}") {
                    sh "pwd"
                    // 构建镜像
                    sh "docker build -t ${serviceName}:${env.BUILD_ID} ."
                    sh "docker login --username=guoguang0536 --password ${DOCKER_HUB_PASSWORD}" // registry.cn-qingdao.aliyuncs.com
                    sh "docker tag ${serviceName}:${env.BUILD_ID} guoguang0536/${serviceName}:${env.BUILD_ID}"
                    script {
                        if("${serviceName}" != "ibole-server-eureka" && "${serviceName}" != "ibole-server-config"){
                            sh "docker push guoguang0536/${serviceName}:${env.BUILD_ID}"
                            echo "构建并推送到远程服务器成功--->"
                        }
                    }
                }
            }
        }

        stage('单元测试') {
            steps {
              echo "starting unitTest......"
//              //注入jacoco插件配置,clean test执行单元测试代码. All tests should pass.
//              sh "mvn org.jacoco:jacoco-maven-plugin:prepare-agent -f ${params.pomPath} clean test -Dautoconfig.skip=true -Dmaven.test.skip=false -Dmaven.test.failure.ignore=true"
//              junit '**/target/surefire-reports/*.xml'
//              //配置单元测试覆盖率要求，未达到要求pipeline将会fail,code coverage.LineCoverage>20%.
//              jacoco changeBuildStatus: true, maximumLineCoverage:"${params.lineCoverage}"
            }
        }
        stage('静态检查') {
            steps {
                echo "starting codeAnalyze with SonarQube......"
//                //sonar:sonar.QualityGate should pass
//                withSonarQubeEnv('SonarQube') {
//                  //固定使用项目根目录${basedir}下的pom.xml进行代码检查
//                  sh "mvn -f pom.xml clean compile sonar:sonar"
//                }
//                script {
//                timeout(10) {
//                    //利用sonar webhook功能通知pipeline代码检测结果，未通过质量阈，pipeline将会fail
//                    def qg = waitForQualityGate()
//                        if (qg.status != 'OK') {
//                            error "未通过Sonarqube的代码质量阈检查，请及时修改！failure: ${qg.status}"
//                        }
//                    }
//                }
            }
        }

        stage('部署测试环境') {
            steps {
                echo "开始部署到----> ${serviceName}......"
                script {
                    echo "即将进入"
                    if ("${serviceName}" == "ibole-server-eureka" || "${serviceName}" == "ibole-server-config"){
                        sh "docker run -p ${servicePort}:${servicePort} --name ${serviceName} -d ${serviceName}:${env.BUILD_ID}"
                        echo '-->> #本机构建成功-->>'
                    }else {
                        sh "apt-get update"
                        sh "apt-get install sshpass"
                        // https://www.cnblogs.com/kaishirenshi/p/7921308.html
//                        sh "sshpass -p ${REMOTE_IP_PASSWORD} ssh root@${REMOTE_IP}"
                        // TODO 删除远程服务器docker镜像
                        sh "pwd"
                        sh "sshpass -f /var/jenkins_home/password.txt ssh -t -t -o StrictHostKeyChecking=no root@${REMOTE_IP} pwd "
                        sh "sshpass -f /var/jenkins_home/password.txt ssh -t -t -o StrictHostKeyChecking=no root@${REMOTE_IP} docker -v "
                        sh "sshpass -f /var/jenkins_home/password.txt ssh -t -t -o StrictHostKeyChecking=no root@${REMOTE_IP} docker pull guoguang0536/${serviceName}:${env.BUILD_ID} "
                        sh "sshpass -f /var/jenkins_home/password.txt ssh -t -t -o StrictHostKeyChecking=no root@${REMOTE_IP} docker run -p ${servicePort}:${servicePort} --name ${serviceName} -d guoguang0536/${serviceName}:${env.BUILD_ID}"
                        echo '-->> #远程主机构建成功-->>'
                     }
                    //这里增加了一个小功能，在服务器上记录了基本部署信息，方便多人使用一套环境时问题排查，storge in {WORKSPACE}/deploy.log  & remoteServer:htdocs/war
//                    Date date = new Date()
//                    def deploylog="${date.toString()},${BUILD_USER} use pipeline  '${JOB_NAME}(${BUILD_NUMBER})' deploy branch ${params.repoBranch} to server ${serverIP}"
//                    println deploylog
//                    sh "echo ${deploylog} >>${WORKSPACE}/deploy.log"
//                    sh "sshpass -p ${serverPasswd} scp ${WORKSPACE}/deploy.log ${serverName}@${serverIP}:htdocs/war"
//                    //jetty restart，重启jetty
//                    sh "sshpass -p ${serverPasswd} ssh ${serverName}@${serverIP} 'bin/jettyrestart.sh' "
                }
            }
        }

        stage('接口自动化测试') {
            steps{
                echo "starting interfaceTest......"
//                script {
//                 //为确保jetty启动完成，加了一个判断，确保jetty服务器启动可以访问后再执行接口层测试。
//                 timeout(5) {
//                     waitUntil {
//                        try {
//                            //确保jetty服务的端口启动成功
//                            sh "nc -z ${serverIP} ${jettyPort}"
//                            //sh "wget -q http://${serverIP}:${jettyPort} -O /dev/null"
//                            return true
//                        } catch (exception) {
//                            return false
//                            }
//                        }
//                    }
//                //将参数IP和Port传入到接口测试的job，需要确保接口测试的job参数可注入
//                 build job: ITEST_JOBNAME, parameters: [string(name: "dubbourl", value: "${serverIP}:${params.dubboPort}")]
//                }
            }
        }

        stage('UI自动化测试') {
            steps {
                echo "starting UITest......"
                //这个项目不需要UI层测试，UI自动化与接口测试的pipeline脚本类似
            }
        }

        stage('性能自动化测试 ') {
            steps {
                echo "starting performanceTest......"
                //视项目需要增加性能的冒烟测试，具体实现后续专文阐述
            }
        }

        //stage('通知人工验收') {
        //    steps {
        //      script {
         //            wrap([$class: 'BuildUser']) {
          //               if (params.isCommitQA == false) {
          //                   echo "不需要通知测试人员人工验收"
          //              } else {
           //                  //邮件通知测试人员人工验收
           //                  mail to: "${QA_EMAIL}",
           //                          subject: "PineLine '${JOB_NAME}' (${BUILD_NUMBER})人工验收通知",
             //                        body: "${BUILD_USER}提交的PineLine '${JOB_NAME}' (${BUILD_NUMBER})进入人工验收环节\n请及时前往${env.BUILD_URL}进行测试验收"
           //              }
 //
              //       }
             //    }
          //   }
       //  }

        // stage('发布系统') {
        //     steps{
        //         echo "starting deploy......"
        //     //    TODO发布环节后续专题阐述
        //     }
        // }
    }
}
