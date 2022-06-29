//package com.redis.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//import redis.clients.jedis.JedisPool;
//
//@Configuration
//@EnableRedisRepositories
//@Slf4j
//public class RedisConfig {
//
//    @Value("${spring.redis.host}")
//    private String redisHost;
//
//    @Value("${spring.redis.port}")
//    private int redisPort;
//
//    private static JedisPool jedisPool;
//
////    @Bean
////    public JedisPool generateJedisPoolFactory() {
////        JedisPoolConfig poolConfig = new JedisPoolConfig();
////        poolConfig.setMaxTotal(128);
////        poolConfig.setMaxIdle(128);
////        poolConfig.setMinIdle(5);
////        poolConfig.setMaxWaitMillis(100000);
////        // Whether to block when the connection is exhausted, false will report an exception, true will block until the timeout, and the default is true
////        poolConfig.setBlockWhenExhausted(Boolean.TRUE);
////        JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379, 100000);
////        // If the Redis password is set, please call the following constructor
//////        JedisPool jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
////        return jedisPool;
////    }
////
////    public static String hgetAll(final String key , final String field) {
////        try (Jedis jedis = jedisPool.getResource()) {
////            return jedis.hget(key , field);
////        } catch (Exception ex) {
////            log.error("Exception caught in hgetAll", ex);
////        }
////        return null;
////    }
//
//    public static void setJedisPool(JedisPool jedisPool) {
//        RedisConfig.jedisPool = jedisPool;
//    }
//
////    @Bean
////    public LettuceConnectionFactory redisConnectionFactory() {
////        // Tạo Standalone Connection tới Redis
////        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
////    }
//
//    @Bean
//    // how to create
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useSingleServer()
//                .setAddress("redis://localhost:6379")
//                .setConnectionPoolSize(10)
//                .setConnectionMinimumIdleSize(5)
//                .setConnectTimeout(30000);
//        return Redisson.create(config);
//    }
//
//    @Bean
//    @Primary
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }
//}
