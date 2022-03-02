/*
 * Copyright (c) 2021 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.nextiva.poc.kafka.producer.controller;

import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.nextiva.poc.kafka.common.model.User;
import com.nextiva.poc.kafka.producer.service.SpringKafkaProducer;

import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("kafka")
public class SpringKafkaController {

    @Autowired
    private SpringKafkaProducer springKafkaProducer;

    @GetMapping("/publish")
    public String publishMessage(@RequestParam String message) {
        springKafkaProducer.produce(message);
        return "Publish successful";
    }

    @PostMapping("/user")
    public String postUser(@RequestBody User user) throws InterruptedException {
        if (isNull(user.getDate())) {
            user.setDate(Instant.now());
        }
        springKafkaProducer.produce(user);
        return "Publish successful";
    }

    @PostMapping("/launch")
    public String launchProducer(@RequestParam(defaultValue = "0") int number) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(() -> {
            for (int i = 0; number <= 0 || i < number; i++) {
                User user = User.builder()
                        .id(UUID.randomUUID().toString())
                        .date(Instant.now())
                        .salary(new Random().nextInt())
                        .build();
                try {
                    springKafkaProducer.produce(user);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.error("Error launching the producer: {}", e.getMessage(), e);
                }
            }
        });
        return "Producer launched successfully";
    }
}
