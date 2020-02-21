#### 分布式配置中心服务器端
1. 中转站,本地服务通过此工程同步github上的配置文件,修改host文映射:config3344.com
2. 启动服务并尝试访问配置文件，有以下五种访问配置规则
   - {application}：配置文件的文件名
   - {profile}：读取的环境
   - {lable}：分支
    /{application}/{profile}[/{lable}]
    /{application}-{profile}.yml
    /{lable}/{application}-{profile}.yml
    /{application}-{profile}.properties
    /{lable}/{application}-{profile}.properties

可用例子（返回格式可能不大相同，但返回值相同）：
http://localhost:9009/服务名称-dev.yml