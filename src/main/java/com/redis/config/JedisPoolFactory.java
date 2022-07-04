package com.redis.config;

import com.redis.constant.Constant;
import com.redis.exception.VNPAYException;
import com.redis.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
@Slf4j
public class JedisPoolFactory {

    /**
     * Initialize Redis connection pool
     */
    @Bean
    public JedisPool generateJedisPoolFactory() {
        try {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(1_00_000);
            poolConfig.setMaxIdle(1_00_000);
            poolConfig.setMinIdle(5);
            poolConfig.setMaxWait(Duration.ofSeconds(100_000));
            // Whether to block when the connection is exhausted,
            // false will report an exception, true will block until the timeout,
            // and the default is true
            poolConfig.setBlockWhenExhausted(Boolean.TRUE);
            return new JedisPool(poolConfig, "127.0.0.1", 6379, 100000);
        } catch (Exception e) {
            throw new VNPAYException(MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR));
        }
    }
}
