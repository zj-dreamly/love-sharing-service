爱分享1024小程序后端

这是我学习慕课的Spring Cloud Alibaba从入门到进阶课程代码。目前此小程序已部署在服务器，此代码clone之后需配合前端代码使用，nacos配置如下： 

content-center-dev.yml 
```xml
mini:
  appKey: 申请的appkey
  appSecret: 申请的appSecret

spring:
  datasource:
    url: jdbc:mysql://localhost/love-sharing?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true
    username: root
    password: root
  application:
    name: user-center
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
      config:
        server-addr: localhost:8848
  jackson:
    date-format: yyyy-MM-dd
    time-zone: GMT+8
management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    health:
      show-details: always
feign:
  hystrix:
    enabled: true
  httpclient:
    # 让feign使用apache httpclient做请求；而不是默认的urlconnection
    enabled: true
    # feign的最大连接数
    max-connections: 200
    # feign单个路径的最大连接数
    max-connections-per-route: 50
```
user-center-dev.yml基本同上，把application name改了就行`user-center`

#### 由于服务器机器配置不高，此版本未使用MQ实现

