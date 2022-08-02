/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.mx.officedepot.poc.kafka.producer.config;

import static com.mx.officedepot.poc.kafka.common.constants.PocKafkaConstants.BOOTSTRAP_SERVER;
import static com.mx.officedepot.poc.kafka.common.constants.PocKafkaConstants.JSON_KAFKA_TOPIC;
import static com.mx.officedepot.poc.kafka.common.constants.PocKafkaConstants.STRING_KAFKA_TOPIC;

import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

//@Configuration
public class KafkaTopicConfiguration {

    //@Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER));
    }

    //@Bean
    public NewTopic stringTopic() {
        return TopicBuilder
                .name(STRING_KAFKA_TOPIC)
                .partitions(3)
                .build();
    }

    //@Bean
    public NewTopic jsonTopic() {
        return TopicBuilder
                .name(JSON_KAFKA_TOPIC)
                .partitions(3)
                .build();
    }
}
