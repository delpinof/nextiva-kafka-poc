package com.mx.officedepot.poc.kafka.producer.config;

import static com.mx.officedepot.poc.kafka.common.constants.PocKafkaConstants.BOOTSTRAP_SERVER;

import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.mx.officedepot.poc.kafka.common.model.User;

@Configuration
public class SpringKafkaProducerConfiguration {

	@Bean
    public KafkaTemplate<String, User> userKafkaTemplate() {
		
        Map<String, Object> config = Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class,
                ProducerConfig.ACKS_CONFIG, "all",
                ProducerConfig.CLIENT_DNS_LOOKUP_CONFIG, "use_all_dns_ips",
                CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL",
                SaslConfigs.SASL_MECHANISM, "PLAIN",
                SaslConfigs.SASL_JAAS_CONFIG, String.format(
                        "%s required username=\"%s\" " + "password=\"%s\";", PlainLoginModule.class.getName(), "L365RXTNYPHOI3R4", "p2AzE40iX2A4QHXOmOfN7DAjKpgA0QzKNnE8IVEyEj4xJ14e3jdhfu0dqek8UPGZ")
                
        );
        
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(config));
        
    }

	@Bean
	public KafkaTemplate<String, String> stringKafkaTemplate() {
		
		Map<String, Object> config = Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class,
                ProducerConfig.ACKS_CONFIG, "all",
                ProducerConfig.CLIENT_DNS_LOOKUP_CONFIG, "use_all_dns_ips",
                CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL",
                SaslConfigs.SASL_MECHANISM, "PLAIN",
                SaslConfigs.SASL_JAAS_CONFIG, String.format(
                        "%s required username=\"%s\" " + "password=\"%s\";", PlainLoginModule.class.getName(), "L365RXTNYPHOI3R4", "p2AzE40iX2A4QHXOmOfN7DAjKpgA0QzKNnE8IVEyEj4xJ14e3jdhfu0dqek8UPGZ")
        );
				
		return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(config));
		
	}

}
