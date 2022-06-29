package com.redis.utils;

import com.redis.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.time.Duration;
import java.util.Objects;

@Component
@Slf4j
public class JedisUtil {
    @Autowired
    private JedisPool jedisPool;

    public Long save(String key , String field , String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hset(key , field , value);
        } catch (Exception e) {
            log.error("Hset redis Ex: ", e);
            throw e;
        }
    }
    public Long expire(String key, Long time) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.expire(key , Math.toIntExact(time));
        } catch (Exception e) {
            log.error("Hset redis Ex: ", e);
            throw e;
        }
    }
    /**
     * Get the specified Value according to the passed in key
     * @param key
     * @return
     * @author hw
     * @date 2018 14 December 2003
     */
    public String get(String key, String field) {
        try(Jedis jedis = jedisPool.getResource()) {
            String result = jedis.hget(key, field);
            return Objects.isNull(result) ? Constant.EMPTY : result;
        } catch (Exception e) {
            throw e;
        }
    }

    /* Determines whether a hash field exists or not. */
    public Boolean exits(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        } catch (Exception e) {
            return false;
        }
    }
}
