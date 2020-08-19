package com.study.springcloud.config.client.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RefreshScope
public class Controller{
	private final Logger logger =LogManager.getLogger(Controller.class);

	@Value("${test.password}")
	private String pwd; //smyserver.url=123
	@Value("${smyserver.url}")
	private String url;
	@Value("${smyserver.port}")
	private String port;
	@Autowired
	EurekaDiscoveryClient discoveryClient;
	@Autowired
    private Registration registration; // 服务注册
	@RequestMapping("/hello")
    public String home() {
		
        return "pwd="+pwd+",url:"+url+",port:"+port;
    }
	
	@RequestMapping("/say")
    public String say() {
		String instanceId = registration.getInstanceId();
		String serviceId = registration.getServiceId();
		String host = registration.getHost();
		int port = registration.getPort();
		logger.info(">instanceId:{},serviceId:{},host:{},port:{}",instanceId,serviceId,host, port);
		
		List<String> services = discoveryClient.getServices();
		services.forEach(t->{
			System.out.println("service:"+t);
		});
		return "hello, host:"+host+",port:"+port;
    }
	@RequestMapping("/echo")
	public String echo() {
		String instanceId = registration.getInstanceId();
		String serviceId = registration.getServiceId();
		String host = registration.getHost();
		int port = registration.getPort();
		logger.info(">instanceId:{},serviceId:{},host:{},port:{}",instanceId,serviceId,host, port);
		return "echo, host:"+host+",port:"+port;
	}
}
