 package com.study.springcloud.feignclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
/*
 * 调用spring.application.name=configclient的工程接口
 * 需要定义一个接口
 */
@FeignClient(name="configclient")
public interface SayHelloClient {
    
	@RequestMapping("/say")
    public String say();
	
	@RequestMapping("/echo")
    public String echo();
}
