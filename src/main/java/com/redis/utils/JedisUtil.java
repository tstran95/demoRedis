package com.redis.utils;

import com.redis.constant.Constant;
import com.redis.exception.VNPAYException;
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
        } catch (VNPAYException e) {
            log.error("Hset redis Ex: {}", MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR));
            throw new VNPAYException(MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR));
        }
    }
    public Long expire(String key, Long time) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.expire(key , Math.toIntExact(time));
        } catch (Exception e) {
            log.error("Hset redis Ex: {}", MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR));
            throw new VNPAYException(MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR));
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
            log.error("Hset redis Ex: {}", MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR));
            throw new VNPAYException(MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR));
        }
    }

    /* Determines whether a hash field exists or not. */
    public boolean exits(String key , String field) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hexists(key, field);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveInSet(String bankCode, String autoGenStr) {
        try (Jedis jedis = jedisPool.getResource()) {
            Long resultOfAdd=  jedis.sadd(bankCode , autoGenStr);
            System.out.println(resultOfAdd + " NUM");
            System.out.println(autoGenStr + " AUTO GEN");
            if ( resultOfAdd == 0) {
                return false;
            }
            expire(bankCode , ProductUtils.getTimeRemaining());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
