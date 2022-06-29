package com.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Slf4j
public class JedisPoolFactory {

    /**
     * Initialize Redis connection pool
     */
    @Bean
    public JedisPool generateJedisPoolFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(5);
//        poolConfig.setMaxWaitMillis(maxWaitMillis);
        // Whether to block when the connection is exhausted, false will report an exception, true will block until the timeout, and the default is true
        poolConfig.setBlockWhenExhausted(Boolean.TRUE);
        JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379, 100000);
        // If the Redis password is set, please call the following constructor
//        JedisPool jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
        return jedisPool;
    }
}
