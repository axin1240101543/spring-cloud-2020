# spring-cloud-2020

#### 介绍
{**以下是码云平台说明，您可以替换此简介**
码云是 OSCHINA 推出的基于 Git 的代码托管平台（同时支持 SVN）。专为开发者提供稳定、高效、安全的云端软件开发协作平台
无论是个人、团队、或是企业，都能够用码云实现代码托管、项目管理、协作开发。企业项目请看 [https://gitee.com/enterprises](https://gitee.com/enterprises)}

#### 软件架构
##### 基础服务层：
* eureka-register：注册中心
* service-hystrix-dashboard：hystrix监控
* zuul：网关
* config-server：配置中心
* admin：健康检查 zipkin
##### 能力层
* service-sms：短信服务
* service-valuation：估价服务
##### 业务层
* api-driver：司机端
* api-passenger：乘客端
##### 通用层
* service-common
##### 测试
* config-client-diy：测试本地给本地发事件
* alibaba-seata：阿里分布式事务解决方案Seata实战

#### 软件版本

### 软件架构说明
1.  Spring Cloud version:Greenwich.SR2
2.  Spring Boot version:2.1.7.RELEASE
3.  IntelliJ IDEA 2019.1.4(Ultimate Edition)
4.  Maven 3.5.4
5.  RabbitMQ 4.2.0

#### 使用说明

1.  启动注册中心 eureka-register
2.  启动配置中心 config-server
3.  启动服务提供者 service-sms、service-valuation
4.  启动服务消费者 api-driver、api-passenger

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
