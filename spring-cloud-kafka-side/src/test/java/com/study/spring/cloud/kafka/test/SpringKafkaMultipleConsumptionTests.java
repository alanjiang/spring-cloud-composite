package com.study.spring.cloud.kafka.test;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.study.spring.cloud.kafka.Listener;
import com.study.spring.cloud.kafka.SpringKafkaExampleApplication;

@RunWith(SpringRunner.class)
@SpringBootConfiguration
@SpringBootTest(classes={SpringKafkaExampleApplication.class})
public class SpringKafkaMultipleConsumptionTests {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private Listener listener;

	@Test
	public void contextLoads() throws InterruptedException {

		for (int i = 0; i < 9; i++) {
			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("SpringKafkaTopic","key:"+1,
					"Messsage:" + i);
			future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
				@Override
				public void onSuccess(SendResult<String, String> result) {
				   ProducerRecord pr = result.getProducerRecord();
					System.out.println("key="+ pr.key()+",value="+pr.value());
					System.out.println("Sent message: " + result);
				}

				@Override
				public void onFailure(Throwable ex) {
					System.out.println("Failed to send message");
				}
			});
		}

		assertThat(this.listener.countDownLatch0.await(60, TimeUnit.SECONDS)).isTrue();
		assertThat(this.listener.countDownLatch1.await(60, TimeUnit.SECONDS)).isTrue();
		assertThat(this.listener.countDownLatch2.await(60, TimeUnit.SECONDS)).isTrue();
	}

}
