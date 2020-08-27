package com.study.spring.cloud.kafka;


import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@RequestMapping("/sendmsg")
    public String sendmsg() {
		for (int i = 0; i < 3; i++) {
			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("topic1","key:"+1,
					"Messsage:" + i);
			future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
				@Override
				public void onSuccess(SendResult<String, String> result) {
				   ProducerRecord<String,String> pr = result.getProducerRecord();
					System.out.println("key="+ pr.key()+",value="+pr.value());
					System.out.println("Sent message: " + result);
				}
				@Override
				public void onFailure(Throwable ex) {
					System.out.println("Failed to send message");
				}
			});
		}
		/*
		for (int i = 0; i < 3; i++) {
			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("topic2","key:"+1,
					"Messsage:" + i);
			future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
				@Override
				public void onSuccess(SendResult<String, String> result) {
				   ProducerRecord<String,String> pr = result.getProducerRecord();
					System.out.println("key="+ pr.key()+",value="+pr.value());
					System.out.println("Sent message: " + result);
				}
				@Override
				public void onFailure(Throwable ex) {
					System.out.println("Failed to send message");
				}
			});
		}
		*/
		
		return "kafka msgs sent!";

    }
	
}
