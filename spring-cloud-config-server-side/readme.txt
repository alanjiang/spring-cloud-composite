1，启动 eureka , 8761

又称服务中心，管理各种服务功能包括服务的注册、发现、熔断、负载、降级等。
任何一个服务都不能直接去掉用，都需要通过注册中心来调用。通过服务中心来获取服务你不需要关注你调用的项目IP地址，由几台服务器组成，每次直接去服务中心获取可以使用的服务去调用既可。
由于各种服务都注册到了服务中心，就有了很多高级功能条件。比如几台服务提供相同服务来做客户端负载均衡（Ribbon）；监控服务器调用成功率来做断路器（Hystrix），移除服务列表中的故障点；
监控服务调用时间来对不同的服务器设置不同的权重、智能路由（Zuul）等等。
Spring Cloud 封装了 Netflix 公司开发的 Eureka 模块来实现服务注册和发现。
Eureka 采用了 C-S 的设计架构。Eureka Server 作为服务注册功能的服务器，它是服务注册中心。而系统中的其他微服务，使用 Eureka 的客户端连接到 Eureka Server，并维持心跳连接。这样系统的维护人员就可以通过 Eureka Server 来监控系统中各个微服务是否正常运行。
Spring Cloud 的一些其他模块（比如Zuul）就可以通过 Eureka Server 来发现系统中的其他微服务，并执行相关的逻辑。
Eureka由两个组件组成：Eureka服务器和Eureka客户端。Eureka服务器用作服务注册服务器。Eureka客户端是一个java客户端，用来简化与服务器的交互、作为轮询负载均衡器，并提供服务的故障切换支持。
Netflix在其生产环境中使用的是另外的客户端，它提供基于流量、资源利用率以及出错状态的加权负载均衡。

java -jar /Users/zhangxiao/eclipse-workspace/spring-cloud-eureka/target/spring-cloud-eureka-side-0.0.1-SNAPSHOT


2,启动配置中心服务 8888

java -jar /Users/zhangxiao/eclipse-workspace/spring-cloud-cipher/target/spring-cloud-cipher-0.0.1-SNAPSHOT.jar



3,启动 spring-cloud-config-client


java  -Dserver.port=9000 -Dspring.application.name=configclient -jar   /Users/zhangxiao/repository/com/study/springcloud/spring-cloud-config-clientside/0.0.1-SNAPSHOT/spring-cloud-config-clientside-0.0.1-SNAPSHOT.jar 

java  -Dserver.port=9001 -Dspring.application.name=configclient -jar   /Users/zhangxiao/repository/com/study/springcloud/spring-cloud-config-clientside/0.0.1-SNAPSHOT/spring-cloud-config-clientside-0.0.1-SNAPSHOT.jar 



4,启动客户端调用服务


java -Dserver.port=8091 -jar /Users/zhangxiao/eclipse-workspace/spring-cloud-feignclient/target/spring-cloud-feignclient-0.0.1-SNAPSHOT.jar 







刷新所有微服务：

查询：GET
http://localhost:8761/actuator
{"_links":{"self":{"href":"http://localhost:8761/actuator","templated":false},"health":{"href":"http://localhost:8761/actuator/health","templated":false},"info":{"href":"http://localhost:8761/actuator/info","templated":false}}}


刷新配置，需要触发config服务器接口

http://localhost:8888/actuator/bus-refresh



4, Ribbon

Ribbon是一个基于HTTP和TCP客户端的负载均衡器，其实feign也使用了ribbon, 只要使用@FeignClient时，ribbon就会自动使用。

4.1 RestTemplate调用

4.2 FeignClient调用

spring-cloud-gateway:

https://blog.csdn.net/qq_38380025/article/details/102968559?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param

https://www.cnblogs.com/zhaosq/p/11675639.html

FeignClient使用：

https://www.jianshu.com/p/0834508b7a6d
使用大全：
https://www.jianshu.com/p/50fd582b739f

spring-cloud-api:
Spring Cloud Gateway 是使用 netty+webflux 实现因此不需要再引入 web 模块。
https://www.cnblogs.com/liukaifeng/p/10055871.html
spring-cloud-api:
比较齐全的介绍：
https://www.cnblogs.com/ityouknow/p/10141740.html

Spring Cloud Gateway 是基于 Spring 生态系统之上构建的 API 网关，包括：Spring 5，Spring Boot 2 和 Project Reactor。Spring Cloud Gateway 旨在提供一种简单而有效的方法来路由到 API，并为它们提供跨领域的关注点，例如：安全性，监视/指标，限流等。由于 Spring 5.0 支持 Netty，Http2，而 Spring Boot 2.0 支持 Spring 5.0，因此 Spring Cloud Gateway 支持 Netty 和 Http2 顺理成章。

但对于服务数量众多、复杂度比较高、规模比较大的业务来说，引入 API 网关也有一系列的好处：

聚合接口使得服务对调用者透明，客户端与后端的耦合度降低
聚合后台服务，节省流量，提高性能，提升用户体验
提供安全、流控、过滤、缓存、计费、监控等 API 管理功能


为什么要使用网关
单体应用：浏览器发起请求到单体应用所在的机器，应用从数据库查询数据原路返回给浏览器，对于单体应用来说是不需要网关的。
微服务：微服务的应用可能部署在不同机房，不同地区，不同域名下。
此时客户端（浏览器/手机/软件工具）想要请求对应的服务，都需要知道机器的具体 IP 或者域名 URL，当微服务实例众多时，这是非常难以记忆的，对于客户端来说也太复杂难以维护。此时就有了网关，客户端相关的请求直接发送到网关，由网关根据请求标识解析判断出具体的微服务地址，再把请求转发到微服务实例。
这其中的记忆功能就全部交由网关来操作了。
spring-cloud-gateway比较全面的说明：

https://zhuanlan.zhihu.com/p/133636384



在SpringCloud微服务体系中，有个很重要的组件就是网关，在1.x版本中都是采用的Zuul网关；

但在2.x版本中，zuul的升级一直跳票，SpringCloud最后自己研发了一个网关替代Zuul，那就是SpringCloud Gateway



雪崩效应常见场景

硬件故障：如服务器宕机，机房断电，光纤被挖断等。

流量激增：如异常流量，重试加大流量等。

缓存穿透：一般发生在应用重启，所有缓存失效时，以及短时间内大量缓存失效时。大量的缓存不命中，使请求直击后端服务，造成服务提供者超负荷运行，引起服务不可用。

程序BUG：如程序逻辑导致内存泄漏，JVM长时间FullGC等。

同步等待：服务间采用同步调用模式，同步等待造成的资源耗尽。




1, zookeeper启动

1.1 zkServer start


1.2 brew services start kafka

  zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties & kafka-server-start /usr/local/etc/kafka/server.properties
  
  
  zookeeper-server-stop /usr/local/etc/kafka/zookeeper.properties & kafka-server-stop /usr/local/etc/kafka/server.properties
  
kafka.common.InconsistentClusterIdException: The Cluster ID q3LP6TM6THioLSeD8B8BlA doesn't match stored clusterId Some(gEMB23NxR46axobvuamr2w) in meta.properties

  brew services start zookeeper

  zkServer start

  brew services start kafka

  zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties & kafka-server-start /usr/local/etc/kafka/server.properties



/usr/local/var/lib/logs

dataDir=/usr/local/var/lib/zookeeper














