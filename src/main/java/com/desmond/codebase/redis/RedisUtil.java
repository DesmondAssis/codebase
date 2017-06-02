package com.desmond.codebase.redis;

import com.desmond.codebase.spring.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.concurrent.TimeUnit;


public class RedisUtil {
    private static final Logger LOG = LoggerFactory.getLogger(RedisUtil.class);

    private static RedisTemplate redisTemplate = getRedisTemplate();
    private static final String REDIS_TEMPLATE_NAME = "redisTemplate";

    public static RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate) SpringContextUtil.getContext().getBean(REDIS_TEMPLATE_NAME);
        }
        return redisTemplate;
    }

    /**
     * 测试指定key是否存在，返回 true表示存在，false不存在
     * @param key
     * @return
     */
    public static boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除指定key
     * @param key
     */
    public static void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除指定keys
     * @param keys
     */
    public static void del(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 返回给定key的value类型
     * @param key
     * @return
     */
    public static DataType type(String key) {
       return redisTemplate.type(key);
    }

    /**
     * 指定过期时间
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    public static boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    public static byte[] rawKey(Object key) {
        if (redisTemplate.getKeySerializer() == null && key instanceof byte[]) {
            return (byte[]) key;
        }
        return redisTemplate.getKeySerializer().serialize(key);
    }

    public static byte[] rawValue(Object value) {
        if (redisTemplate.getValueSerializer() == null && value instanceof byte[]) {
            return (byte[]) value;
        }
        return redisTemplate.getValueSerializer().serialize(value);
    }



    /**
     * 获取过期时间 (秒)
     * @param key
     * @return
     */
    public static long ttl(String key) {
       return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public static void set(String key, String value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public static boolean setIfAbsent(String key, String value) {
       return redisTemplate.opsForValue().setIfAbsent(key, value);
    }
}
