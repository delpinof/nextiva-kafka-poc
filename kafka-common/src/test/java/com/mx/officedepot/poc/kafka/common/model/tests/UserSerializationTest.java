package com.mx.officedepot.poc.kafka.common.model.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.Instant;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mx.officedepot.poc.kafka.common.model.User;

public class UserSerializationTest {

	public static ObjectMapper objectMapper;

	public static String jsonPayload;

	public static User user;

	@BeforeAll
	public static void init() throws IOException {

		objectMapper = new ObjectMapper();

		JavaTimeModule module = new JavaTimeModule();

		objectMapper.registerModule(module);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		jsonPayload = new String(UserSerializationTest.class.getResourceAsStream("/payload.json").readAllBytes());
		user = User.builder().id("95a1af9d-fe89-4d3e-a274-687ba3c045d5")
				.date(Instant.parse("2022-07-29T18:57:07.107268Z"))
					.salary(150)
						.build();

	}

	@Test
	public void testSerialization() throws JsonProcessingException {

		String result = objectMapper.writeValueAsString(user);
		assertEquals(jsonPayload, result);

	}

	@Test
	public void testDeserialization() throws JsonProcessingException {

		User result = objectMapper.readValue(jsonPayload, User.class);
		assertEquals(user, result);

	}

}
