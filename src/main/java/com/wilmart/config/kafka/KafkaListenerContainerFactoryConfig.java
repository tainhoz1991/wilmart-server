package com.wilmart.config.kafka;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

//@EnableKafka
@Configuration
public class KafkaListenerContainerFactoryConfig {
    private static final Logger logger = LogManager.getLogger(KafkaListenerContainerFactoryConfig.class);

    private final ProducerFactory<String, Object> producerFactory;
    private final ConsumerFactory<String, Object> consumerFactory;

    public KafkaListenerContainerFactoryConfig(ProducerFactory<String, Object> producerFactory,
                                               ConsumerFactory<String, Object> consumerFactory) {
        this.producerFactory = producerFactory;
        this.consumerFactory = consumerFactory;
    }

    // container factory for no reply
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setReplyTemplate(kafkaTemplate());
        return factory;
    }

    // container factory for reply
    @Bean
    public KafkaMessageListenerContainer<String, Object> replyContainer(ConsumerFactory<String, Object> cf) {
        ContainerProperties containerProperties = new ContainerProperties("WfReplies" + System.currentTimeMillis());
        return new KafkaMessageListenerContainer<>(cf, containerProperties);
    }

    @Bean
    public ReplyingKafkaTemplate<String, Object, Object> replyKafkaTemplate(ProducerFactory<String, Object> pf,
                                                                            KafkaMessageListenerContainer<String, Object> container) {
        return new ReplyingKafkaTemplate<>(pf, container);

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactoryReply(ProducerFactory<String, Object> pf,
                                                                                                      KafkaMessageListenerContainer<String, Object> container) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setReplyTemplate(replyKafkaTemplate(pf, container));

        return factory;
    }
}
