package com.study.springcloud.config.client;

import java.util.Iterator;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;

public class MQListener {

	
	@KafkaListeners({@KafkaListener(id="test",topics="topic1"), @KafkaListener(id="test2",topics="topic2")})
	public void listen(ConsumerRecord<String, String> msg) {
		Headers headers = msg.headers();
		Iterator<Header> its =  headers.iterator();
		while(its.hasNext()){
			
			 Header header = its.next();
			 System.out.println("Headers:"+header.key()+","+header.value());
		}
		System.out.println("******msg******"+msg);
		System.out.println("******1******");
		System.out.println("=>key:"+msg.key()+",value:"+msg.value());
		  
	}
	
}
