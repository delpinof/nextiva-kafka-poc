package com.mx.officedepot.poc.kafka.common.constants;

public interface PocKafkaConstants {

    String BOOTSTRAP_SERVER = "pkc-lzvrd.us-west4.gcp.confluent.cloud:9092"; //10.0.45.14:9092
    //String SCHEMA_REGISTRY = "http://localhost:8081";
    String HELLO_WORLD_TOPIC = "orders";
    String STRING_KAFKA_TOPIC = "orders";
    String JSON_KAFKA_TOPIC = "orders";
    String KAFKA_STRING_CONSUMER_GROUP_ID = "string-message-group1";
    String KAFKA_JSON_CONSUMER_GROUP_ID = "json-message-group1";
}
