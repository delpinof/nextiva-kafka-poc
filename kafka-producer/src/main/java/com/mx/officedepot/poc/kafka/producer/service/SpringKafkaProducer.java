/*
 * Copyright (c) 2021 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.mx.officedepot.poc.kafka.producer.service;

import static com.mx.officedepot.poc.kafka.common.constants.PocKafkaConstants.JSON_KAFKA_TOPIC;
import static com.mx.officedepot.poc.kafka.common.constants.PocKafkaConstants.STRING_KAFKA_TOPIC;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mx.officedepot.poc.kafka.common.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpringKafkaProducer {

    @Autowired
    @Qualifier("stringKafkaTemplate")
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    @Qualifier("userKafkaTemplate")
    private KafkaTemplate<String, User> userKafkaTemplate;

    public void produce(String message) {
        log.info("Producing to kafka, topic={}, message={}", STRING_KAFKA_TOPIC, message);
        kafkaTemplate.send(STRING_KAFKA_TOPIC, message);
    }

    public void produceWithHeaders(Map<String, String> headers, String message) {
        log.info("Producing to kafka, topic={}, headers={}, message={}", JSON_KAFKA_TOPIC, headers, message);
        var record = new ProducerRecord<String, String>(JSON_KAFKA_TOPIC, message);
        for (String key : headers.keySet()) {
            record.headers().add(key, headers.get(key).getBytes());
        }
        kafkaTemplate.send(record);
    }

    public void produce(User user) throws InterruptedException {
        log.info("Producing to kafka, topic={}, user={}", JSON_KAFKA_TOPIC, user);
        userKafkaTemplate.send(JSON_KAFKA_TOPIC, user);
    }
}
