package com.sana.base.framework.cache.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author LON
 * @create 2025/7/12
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        configureRedisTemplate(factory, template);
        return template;
    }

    private RedisTemplate<String, Object> configureRedisTemplate(RedisConnectionFactory factory, RedisTemplate<String, Object> template) {
        if (template == null || factory == null) {
            throw new IllegalArgumentException("RedisTemplate or RedisConnectionFactory cannot be null");
        }

        // 设置键的序列化器
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());

        // 设置值的序列化器
        template.setValueSerializer(RedisSerializer.json());
        template.setHashValueSerializer(RedisSerializer.json());

        // 设置 Redis 连接工厂
        template.setConnectionFactory(factory);

        return template;
    }

}
