package com.wilmart.config.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class KafkaProperties {

    private String kafkaBootstrapServers = "127.0.0.1:9092";

    private String kafkaTopicRequestReplyTopic;

    private String kafkaTopicRequestTopic;

    private String kafkaTopicReplyTopic;

    private Long kafkaRequestReplyTimeout;

    private String kafkaGroupId = "new_group";
}
