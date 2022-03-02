/*
 * Copyright (c) 2021 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.nextiva.poc.kafka.common.constants;

public interface NextivaPocKafkaConstants {

    String BOOTSTRAP_SERVER = "localhost:9092"; //10.0.45.14:9092
    String SCHEMA_REGISTRY = "http://localhost:8081";
    String HELLO_WORLD_TOPIC = "hello-world-topic";
    String STRING_KAFKA_TOPIC = "kafka-example-string";
    String JSON_KAFKA_TOPIC = "kafka-example-json";
    String KAFKA_STRING_CONSUMER_GROUP_ID = "string-message-group1";
    String KAFKA_JSON_CONSUMER_GROUP_ID = "json-message-group1";
}
