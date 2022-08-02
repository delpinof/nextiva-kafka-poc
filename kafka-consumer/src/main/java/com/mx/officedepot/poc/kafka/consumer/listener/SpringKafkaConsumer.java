package com.mx.officedepot.poc.kafka.consumer.listener;

import static com.mx.officedepot.poc.kafka.common.constants.PocKafkaConstants.JSON_KAFKA_TOPIC;
import static com.mx.officedepot.poc.kafka.common.constants.PocKafkaConstants.KAFKA_JSON_CONSUMER_GROUP_ID;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.mx.officedepot.poc.kafka.common.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpringKafkaConsumer {

	/*
	@KafkaListener(topics = STRING_KAFKA_TOPIC, groupId = KAFKA_STRING_CONSUMER_GROUP_ID)
	public void consume(String message) throws InterruptedException {

		Thread.sleep(300);
		log.info("Consuming from kafka, topic={}, message={}", STRING_KAFKA_TOPIC, message);

	}
	*/

	@KafkaListener(topics = JSON_KAFKA_TOPIC, topicPartitions = @TopicPartition(topic = JSON_KAFKA_TOPIC, partitions = "1") , groupId = KAFKA_JSON_CONSUMER_GROUP_ID, containerFactory = "userKafkaListenerContainerFactory")
	public void consumeUser(User user, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition, @Header(KafkaHeaders.OFFSET) String offset) throws InterruptedException {

		Thread.sleep(300);
		log.info("Partition: {}", partition);
		log.info("Offset: {}", offset);
		log.info("Consuming from kafka, topic={}, user={}", JSON_KAFKA_TOPIC, user);

	}

}
