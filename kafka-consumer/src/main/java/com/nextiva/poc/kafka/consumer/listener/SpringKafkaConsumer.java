/*
 * Copyright (c) 2021 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.nextiva.poc.kafka.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.nextiva.poc.kafka.common.model.User;

import lombok.extern.slf4j.Slf4j;

import static com.nextiva.poc.kafka.common.constants.NextivaPocKafkaConstants.JSON_KAFKA_TOPIC;
import static com.nextiva.poc.kafka.common.constants.NextivaPocKafkaConstants.KAFKA_JSON_CONSUMER_GROUP_ID;
import static com.nextiva.poc.kafka.common.constants.NextivaPocKafkaConstants.KAFKA_STRING_CONSUMER_GROUP_ID;
import static com.nextiva.poc.kafka.common.constants.NextivaPocKafkaConstants.STRING_KAFKA_TOPIC;

@Slf4j
@Service
public class SpringKafkaConsumer {

    @KafkaListener(topics = STRING_KAFKA_TOPIC, groupId = KAFKA_STRING_CONSUMER_GROUP_ID)
    public void consume(String message) throws InterruptedException {
        Thread.sleep(300);
        log.info("Consuming from kafka, topic={}, message={}", STRING_KAFKA_TOPIC, message);
    }

    @KafkaListener(topics = JSON_KAFKA_TOPIC, groupId = KAFKA_JSON_CONSUMER_GROUP_ID,
            containerFactory = "userKafkaListenerContainerFactory")
    public void consumeUser(User user) throws InterruptedException {
        Thread.sleep(300);
        log.info("Consuming from kafka, topic={}, user={}", JSON_KAFKA_TOPIC, user);
    }

}
