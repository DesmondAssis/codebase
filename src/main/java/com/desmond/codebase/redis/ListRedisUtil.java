//package com.desmond.codebase.redis;
//
//import com.dianping.cat.Cat;
//import com.dianping.cat.message.Transaction;
//import com.xish.core.enums.RedisOpEnum;
//import com.xish.core.enums.RedisOpTypeEnum;
//import com.xish.core.util.cat.CatUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.core.ListOperations;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import static com.xish.core.constants.Constants.CACHE_PREFIX;
//
///**
// * Created by zhanghuyi on 16/6/12.
// */
//public class ListRedisUtil {
//
//    private static final Logger LOG = LoggerFactory.getLogger(ListRedisUtil.class);
//
//    private static ListOperations listOperations = RedisUtil.getRedisTemplate().opsForList();
//
//    /**
//     * 在key对应list的头部添加字符串元素
//     * @param key
//     * @param value
//     */
//    public static long lpush(String key, Object value, long seconds) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.LPUSH.getName());
//        long lon = 0;
//        try {
//            lon = listOperations.leftPush(key, value);
//
//            CatUtil.logEvent(RedisOpTypeEnum.LIST.getName(), key, RedisOpEnum.LPUSH.getName());
//            t.setStatus(Transaction.SUCCESS);
//        } catch (Exception e) {
//            t.setStatus(e);
//            LOG.error(e.getMessage(), e);
//        } finally {
//            t.complete();
//        }
//        if (lon > 0) {
//            RedisUtil.expire(key, seconds, TimeUnit.SECONDS);
//        }
//        return lon;
//    }
//
//    /**
//     * 从list的头部弹出元素
//     * @param key
//     * @return
//     */
//    public static Object lpop(String key){
//        return listOperations.leftPop(key);
//    }
//
//    /**
//     * 在key对应list的尾部添加字符串元素
//     * @param key
//     * @param value
//     */
//    public static long rpush(String key, Object value, long seconds) {
//        long lon = rpush(key, value);
//        if (lon > 0) {
//            RedisUtil.expire(key, seconds, TimeUnit.SECONDS);
//        }
//        return lon;
//    }
//
//    /**
//     * 在key对应list的尾部添加字符串元素
//     * @param key
//     * @param value
//     */
//    public static long rpush(String key, Object value) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.RPUSH.getName());
//        try {
//            long lon = listOperations.rightPush(key, value);
//
//            CatUtil.logEvent(RedisOpTypeEnum.LIST.getName(), key, RedisOpEnum.RPUSH.getName());
//            t.setStatus(Transaction.SUCCESS);
//            return lon;
//        } catch (RuntimeException e) {
//            t.setStatus(e);
//            Cat.logError(e);
//            throw new RuntimeException(e);
//        } finally {
//            t.complete();
//        }
//    }
//
//    /**
//     * 返回key对应list的长度
//     * @param key
//     * @return
//     */
//    public static long llen(String key) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.LLEN.getName());
//        try {
//            long length = listOperations.size(key);
//
//            CatUtil.logEvent(RedisOpTypeEnum.LIST.getName(), key, RedisOpEnum.LLEN.getName());
//            t.setStatus(Transaction.SUCCESS);
//            return length;
//        } catch (RuntimeException e) {
//            t.setStatus(e);
//            Cat.logError(e);
//            throw new RuntimeException(e);
//        } finally {
//            t.complete();
//        }
//    }
//
//    /**
//     * 返回指定区间内的元素，下标从0开始，负值表示从后面计算，-1表示倒数第一个元素 ，key不存在返回空列表
//     * @param key
//     * @return
//     */
//    public static List<Object> lrange(String key, long start, long end) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.LRANGE.getName());
//        try {
//            List<Object> list = listOperations.range(key, start, end);
//
//            CatUtil.logEvent(RedisOpTypeEnum.LIST.getName(), key, RedisOpEnum.LRANGE.getName());
//            t.setStatus(Transaction.SUCCESS);
//            return list;
//        } catch (RuntimeException e) {
//            t.setStatus(e);
//            Cat.logError(e);
//            throw new RuntimeException(e);
//        } finally {
//            t.complete();
//        }
//    }
//
//    /**
//     * 返回所有
//     * @param key
//     * @return
//     */
//    public static List<Object> lrangeAll(String key) {
//        return lrange(key, 0, -1);
//    }
//
//}
