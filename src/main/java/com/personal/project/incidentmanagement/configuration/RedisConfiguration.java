package com.personal.project.incidentmanagement.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.project.incidentmanagement.model.Incident;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {
    @Bean
    public RedisTemplate<String, Incident> redisTemplate() {
        RedisTemplate<String, Incident> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;

    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory("localhost", 6379);
    }

    @Bean
    public Jackson2JsonRedisSerializer<Incident> jackson2JsonRedisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();

        return new Jackson2JsonRedisSerializer<>(objectMapper, Incident.class);
    }

}
