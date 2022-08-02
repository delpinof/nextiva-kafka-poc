package com.mx.officedepot.poc.kafka.consumer.config;

import static com.mx.officedepot.poc.kafka.common.constants.PocKafkaConstants.BOOTSTRAP_SERVER;
import static com.mx.officedepot.poc.kafka.common.constants.PocKafkaConstants.KAFKA_JSON_CONSUMER_GROUP_ID;
import static com.mx.officedepot.poc.kafka.common.constants.PocKafkaConstants.KAFKA_STRING_CONSUMER_GROUP_ID;

import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.mx.officedepot.poc.kafka.common.model.User;

@EnableKafka //tells spring to search for classes with @KafkaListener annotation
@Configuration
public class SpringKafkaConsumerConfiguration {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    	
        Map<String, Object> config = Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.GROUP_ID_CONFIG, KAFKA_STRING_CONSUMER_GROUP_ID,
                ProducerConfig.ACKS_CONFIG, "all",
                ProducerConfig.CLIENT_DNS_LOOKUP_CONFIG, "use_all_dns_ips",
                CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL",
                SaslConfigs.SASL_MECHANISM, "PLAIN",
                SaslConfigs.SASL_JAAS_CONFIG, String.format(
                        "%s required username=\"%s\" " + "password=\"%s\";", PlainLoginModule.class.getName(), "L365RXTNYPHOI3R4", "p2AzE40iX2A4QHXOmOfN7DAjKpgA0QzKNnE8IVEyEj4xJ14e3jdhfu0dqek8UPGZ")
        );
        
        DefaultKafkaConsumerFactory<String, String> consumerFactory =
                new DefaultKafkaConsumerFactory<>(config);
        
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        
        factory.setConsumerFactory(consumerFactory);
        
        return factory;
        
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, User> userKafkaListenerContainerFactory() {
        
    	Map<String, Object> config = Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                ConsumerConfig.GROUP_ID_CONFIG, KAFKA_JSON_CONSUMER_GROUP_ID,
                ConsumerConfig.CLIENT_DNS_LOOKUP_CONFIG, "use_all_dns_ips",
                ConsumerConfig.CLIENT_ID_CONFIG, "sprimh-kafka-consumer",
                CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL",
                SaslConfigs.SASL_MECHANISM, "PLAIN",
                SaslConfigs.SASL_JAAS_CONFIG, String.format(
                		"%s required username=\"%s\" " + "password=\"%s\";", PlainLoginModule.class.getName(), "L365RXTNYPHOI3R4", "p2AzE40iX2A4QHXOmOfN7DAjKpgA0QzKNnE8IVEyEj4xJ14e3jdhfu0dqek8UPGZ")
        );

    	DefaultKafkaConsumerFactory<String, User> consumerFactory =
                new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(User.class));
        
    	ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        
    	factory.setConsumerFactory(consumerFactory);
        
    	return factory;
    	
    }

}
