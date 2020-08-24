package com.study.spring.cloud.kafka;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.kafka.annotation.TopicPartition;

public class Listener {
	
	/*
	public CountDownLatch countDownLatch0 = new CountDownLatch(3);
	public CountDownLatch countDownLatch1 = new CountDownLatch(3);
	public CountDownLatch countDownLatch2 = new CountDownLatch(3);

	@KafkaListener(id = "id0", topicPartitions = { @TopicPartition(topic = "SpringKafkaTopic", partitions = { "0" }) })
	public void listenPartition0(ConsumerRecord<?, ?> record) {
		System.out.println("Listener Id0, Thread ID: " + Thread.currentThread().getId());
		System.out.println("Received: " + record);
		countDownLatch0.countDown();
	}

	@KafkaListener(id = "id1", topicPartitions = { @TopicPartition(topic = "SpringKafkaTopic", partitions = { "1" }) })
	public void listenPartition1(ConsumerRecord<?, ?> record) {
		System.out.println("Listener Id1, Thread ID: " + Thread.currentThread().getId());
		System.out.println("Received: " + record);
		countDownLatch1.countDown();
	}

	@KafkaListener(id = "id2", topicPartitions = { @TopicPartition(topic = "SpringKafkaTopic", partitions = { "2" }) })
	public void listenPartition2(ConsumerRecord<?, ?> record) {
		System.out.println("Listener Id2, Thread ID: " + Thread.currentThread().getId());
		System.out.println("Received: " + record);
		countDownLatch2.countDown();
	}*/
	
	/*
	@KafkaListeners({@KafkaListener(id="test",topics="topic1"), @KafkaListener(id="test2",topics="topic2")})
	public void listen(ConsumerRecord<String,String> msg) {
		Headers headers = msg.headers();
		Iterator<Header> its =  headers.iterator();
		while(its.hasNext()){
			
			 Header header = its.next();
			 System.out.println("Headers:"+header.key()+","+header.value());
		}
		//KafkaConsumer.<init>(KafkaConsumer.java:799
		KafkaConsumer kf;
		
		System.out.println("=>key:"+msg.key()+",value:"+msg.value());
		  
	}
	*/
	
}
