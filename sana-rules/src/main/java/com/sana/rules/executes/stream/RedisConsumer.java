package com.sana.rules.executes.stream;

import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.cache.redis.stream.RedisStreamConfigProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

import java.time.Duration;
import java.util.concurrent.ExecutorService;

/**
 * @author LON
 * @data 2025/9/27
 */
@Configuration
public class RedisConsumer {
    /**
     * 注入Redis缓存操作工具类。
     */
    @Resource
    private RedisUtils redisUtils;

    @Resource
    private ConsumerRuleOne consumerRuleOne;


    @Resource
    private ConsumerRuleTwo consumerRuleTwo;

    @Resource
    private ConsumerRuleJob consumerRuleJob;
    @Resource(name = "redisStreamTaskExecutor")
    private ExecutorService redisStreamExecutor;

    @Resource
    private RedisStreamConfigProperties redisStreamConfigProperties;

    /**
     * 配置并创建StreamMessageListenerContainer，用于监听和处理Redis流消息。
     *
     * @return 配置好的StreamMessageListenerContainer实例
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer() {


        // 配置StreamMessageListenerContainer的选项
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> options =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                        .builder()
                        .batchSize(redisStreamConfigProperties.getBatchSize())
                        .executor(runnable -> redisStreamExecutor.submit(runnable)) // 使用注入的线程池
                        .pollTimeout(Duration.ofSeconds(redisStreamConfigProperties.getPollTimeoutSeconds()))
                        //.errorHandler(new StreamErrorHandler()) // 可选异常处理
                        .build();

        // 创建StreamMessageListenerContainer实例
        StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer =
                StreamMessageListenerContainer.create(redisUtils.getRedisConnectionFactory(), options);


        // 注册消费者1到容器
        streamMessageListenerContainer.receiveAutoAck(Consumer.from(redisStreamConfigProperties.getStreams().get(0).getGroup(), "consumerRuleOne"),
                StreamOffset.create(redisStreamConfigProperties.getStreams().get(0).getName(), ReadOffset.lastConsumed()), consumerRuleOne);

        // 注册消费者2到容器
        streamMessageListenerContainer.receiveAutoAck(Consumer.from(redisStreamConfigProperties.getStreams().get(1).getGroup(), "consumerRuleTwo"),
                StreamOffset.create(redisStreamConfigProperties.getStreams().get(1).getName(), ReadOffset.lastConsumed()), consumerRuleTwo);
        // 注册消费者3到容器
        streamMessageListenerContainer.receiveAutoAck(Consumer.from(redisStreamConfigProperties.getStreams().get(2).getGroup(), "ConsumerRuleJob"),
                StreamOffset.create(redisStreamConfigProperties.getStreams().get(2).getName(), ReadOffset.lastConsumed()), consumerRuleJob);
        // 返回配置好的StreamMessageListenerContainer实例
        return streamMessageListenerContainer;
    }
}
