package com.wilmart.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

public class KafkaTopicConfig {
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic("get_list_users", 1, (short) 1);
    }

    @Bean
    public NewTopic topic2() {
        return new NewTopic("create_user", 1, (short) 1);
    }

    @Bean
    public NewTopic topic3() {
        return new NewTopic("update_user", 1, (short) 1);
    }

    @Bean
    public NewTopic topic4() {
        return new NewTopic("delete_user", 1, (short) 1);
    }

    @Bean
    public NewTopic topic5() {
        return new NewTopic("get_info_user", 1, (short) 1);
    }

    @Bean
    public NewTopic topic6() {
        return new NewTopic("find_user_by_fullname", 1, (short) 1);
    }

    @Bean
    public NewTopic topic7() {
        return new NewTopic("find_user_by_gender", 1, (short) 1);
    }

    @Bean
    public NewTopic topic8() {
        return new NewTopic("find_user_by_department", 1, (short) 1);
    }

    @Bean
    public NewTopic topic9() {
        return new NewTopic("find_user_by_email", 1, (short) 1);
    }
}
