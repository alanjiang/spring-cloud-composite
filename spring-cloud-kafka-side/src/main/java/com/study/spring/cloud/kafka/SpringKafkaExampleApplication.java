package com.study.spring.cloud.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringKafkaExampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaExampleApplication.class, args);
	}

	
	@Bean
	Listener listener(){
		
		return new Listener();
	}
}
