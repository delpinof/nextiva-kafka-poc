package com.mx.officedepot.poc.kafka.common.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private String id;
	
	private Instant date;
	
	private int salary;
	
}
