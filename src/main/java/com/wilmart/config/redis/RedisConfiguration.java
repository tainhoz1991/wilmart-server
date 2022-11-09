//package com.wilmart.config.redis;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericToStringSerializer;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfiguration {
//
//    @Primary
//    @Bean
//    public RedisTemplate<String,String> redisTemplate() {
//        RedisTemplate<String, String> template = new RedisTemplate<String, String>();
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().build();
//
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new GenericToStringSerializer<String>(String.class));
//        template.setHashValueSerializer(new JdkSerializationRedisSerializer());
//        template.setValueSerializer(new JdkSerializationRedisSerializer());
//
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(configuration, jedisClientConfiguration);
//
//        template.setConnectionFactory(jedisConnectionFactory);
//        return template;
//    }
////    @Bean
////    JedisConnectionFactory jedisConnectionFactory() {
////        return new JedisConnectionFactory();
////    }
////
////    @Bean
////    public RedisTemplate<String, Object> redisTemplate() {
////        RedisTemplate<String, Object> template = new RedisTemplate<>();
////        template.setConnectionFactory(jedisConnectionFactory());
////        return template;
////    }
//}
