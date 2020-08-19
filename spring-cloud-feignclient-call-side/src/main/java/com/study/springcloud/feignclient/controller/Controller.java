package com.study.springcloud.feignclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springcloud.feignclient.feign.SayHelloClient;


@RestController
public class Controller {
    @Autowired
    private SayHelloClient sayHelloClient;
    /*
     * @调用真身：spring.application.name=configclient的微服务 
     */
	@RequestMapping("/say")
    public String say() {
		return sayHelloClient.say();
	}
	@RequestMapping("/echo")
    public String echo() {
		return sayHelloClient.echo();
	}
	 
}
