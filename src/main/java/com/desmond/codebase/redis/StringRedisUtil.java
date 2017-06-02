//package com.desmond.codebase.redis;
//
//import com.desmond.codebase.spring.SpringContextUtil;
//import com.dianping.cat.Cat;
//import com.dianping.cat.message.Transaction;
//import com.xish.core.enums.RedisOpEnum;
//import com.xish.core.enums.RedisOpTypeEnum;
//import com.xish.core.util.SpringContextUtil;
//import com.xish.core.util.cat.CatUtil;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//
//import java.util.concurrent.TimeUnit;
//
//import static com.xish.core.constants.Constants.CACHE_PREFIX;
//
///**
// * Created by zhanghuyi on 16/6/12.
// *
// * 兼容以前字符串类型
// */
//public class StringRedisUtil {
//
//    private static ValueOperations<String, String> valueOperations = getRedisTemplate().opsForValue();
//
//    private static StringRedisTemplate redisTemplate = getRedisTemplate();
//    private static final String REDIS_TEMPLATE_NAME = "redisTemplate";
//
//    private static StringRedisTemplate getRedisTemplate() {
//        if (redisTemplate == null) {
//            redisTemplate = (StringRedisTemplate) SpringContextUtil.getContext().getBean(REDIS_TEMPLATE_NAME);
//        }
//        return redisTemplate;
//    }
//
//    /**
//     * 设置key对应的值为string类型的value
//     * @param key
//     * @param value
//     */
//    public static void set(String key, String value) {
//        valueOperations.set(key, value);
//    }
//
//    /**
//     * 设置key对应的值为string类型的value
//     * @param key
//     * @param value
//     * @param time
//     * @param timeUnit
//     */
//    public static void set(String key, String value, long time, TimeUnit timeUnit) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.SET.getName());
//        try {
//            valueOperations.set(key, value, time, timeUnit);
//
//            CatUtil.logEvent(RedisOpTypeEnum.STRING.getName(), key, RedisOpEnum.SET.getName());
//            t.setStatus(Transaction.SUCCESS);
//        } catch (Exception e) {
//            t.setStatus(e);
//            Cat.logError(e);
//        } finally {
//            t.complete();
//        }
//
//    }
//
//    /**
//     * 如果key不存在 设置key对应的值为string类型的value 返回true
//     *     key已经存在，返回false。 nx 是not exist的意思
//     * @param key
//     * @param value
//     * @param seconds
//     */
//    public static boolean setnx(String key, String value, long seconds) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.SETNX.getName());
//        boolean flag;
//        try {
//            flag = valueOperations.setIfAbsent(key, value);
//
//            CatUtil.logEvent(RedisOpTypeEnum.STRING.getName(), key, RedisOpEnum.SETNX.getName());
//            t.setStatus(Transaction.SUCCESS);
//        } catch (RuntimeException e) {
//            t.setStatus(e);
//            Cat.logError(e);
//            throw new RuntimeException(e);
//        } finally {
//            t.complete();
//        }
//        if (flag) {
//            RedisUtil.expire(key, seconds, TimeUnit.SECONDS);
//        }
//        return flag;
//    }
//
//    public static Object get(String key) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.GET.getName());
//        try {
//            Object obj = valueOperations.get(key);
//
//            CatUtil.logEvent(RedisOpTypeEnum.STRING.getName(), key, RedisOpEnum.GET.getName());
//            t.setStatus(Transaction.SUCCESS);
//            return obj;
//        } catch (RuntimeException e) {
//            t.setStatus(e);
//            Cat.logError(e);
//            throw new RuntimeException(e);
//        } finally {
//            t.complete();
//        }
//    }
//
//    public static long increment(String key, long v) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.GET.getName());
//        try {
//            long obj = valueOperations.increment(key, v);
//
//            CatUtil.logEvent(RedisOpTypeEnum.STRING.getName(), key, RedisOpEnum.GET.getName());
//            t.setStatus(Transaction.SUCCESS);
//            return obj;
//        } catch (RuntimeException e) {
//            t.setStatus(e);
//            Cat.logError(e);
//            throw new RuntimeException(e);
//        } finally {
//            t.complete();
//        }
//    }
//
//}
