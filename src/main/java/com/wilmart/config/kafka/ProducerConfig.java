package com.wilmart.config.kafka;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfig {
    public static final int MAX_MESSAGE_BYTES = 20971520;
    private final KafkaProperties kafkaProperties;

    public ProducerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getKafkaBootstrapServers());
        props.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(org.apache.kafka.clients.producer.ProducerConfig.RETRIES_CONFIG, 0);
        props.put(org.apache.kafka.clients.producer.ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(org.apache.kafka.clients.producer.ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(org.apache.kafka.clients.producer.ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(org.apache.kafka.clients.producer.ProducerConfig.MAX_REQUEST_SIZE_CONFIG, MAX_MESSAGE_BYTES);
        return props;
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
}
