//package com.desmond.codebase.redis;
//
//import com.dianping.cat.Cat;
//import com.dianping.cat.message.Transaction;
//import com.xish.core.enums.RedisOpEnum;
//import com.xish.core.enums.RedisOpTypeEnum;
//import com.xish.core.util.cat.CatUtil;
//import org.springframework.data.redis.core.SetOperations;
//
//import java.util.Set;
//
//import static com.xish.core.constants.Constants.CACHE_PREFIX;
//
///**
// * Created by zhanghuyi on 16/6/12.
// */
//public class SetRedisUtil {
//
//    private static SetOperations setOperations = RedisUtil.getRedisTemplate().opsForSet();
//
//    /**
//     * 添加到key对应的set集合中
//     * @param key
//     * @param values
//     */
//    public static long sadd(String key, Object... values) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.SADD.getName());
//        try {
//            long lon = setOperations.add(key, values);
//
//            CatUtil.logEvent(RedisOpTypeEnum.SET.getName(), key, RedisOpEnum.SADD.getName());
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
//     * 从key对应set中移除给定元素
//     * @param key
//     * @param values
//     * @return
//     */
//    public static long srem(String key, Object... values) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.SREM.getName());
//        try {
//            long lon = setOperations.remove(key, values);
//
//            CatUtil.logEvent(RedisOpTypeEnum.SET.getName(), key, RedisOpEnum.SREM.getName());
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
//     * 判断是否在set中
//     * @param key
//     * @param value
//     * @return
//     */
//    public static boolean sismember(String key, Object value) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.SISMEMBER.getName());
//        try {
//            boolean isMember = setOperations.isMember(key, value);
//
//            CatUtil.logEvent(RedisOpTypeEnum.SET.getName(), key, RedisOpEnum.SISMEMBER.getName());
//            t.setStatus(Transaction.SUCCESS);
//            return isMember;
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
//     * 返回key对应set的所有元素，结果是无序的
//     * @param key
//     * @return
//     */
//    public static Set smembers(String key) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.SMEMBERS.getName());
//        try {
//            Set set = setOperations.members(key);
//
//            CatUtil.logEvent(RedisOpTypeEnum.SET.getName(), key, RedisOpEnum.SMEMBERS.getName());
//            t.setStatus(Transaction.SUCCESS);
//            return set;
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
//     * 返回key对应set的所有元素，结果是无序的
//     * @param key
//     * @return
//     */
//    public static long scard(String key) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.SCARD.getName());
//        try {
//            long count = setOperations.size(key);
//
//            CatUtil.logEvent(RedisOpTypeEnum.SET.getName(), key, RedisOpEnum.SCARD.getName());
//            t.setStatus(Transaction.SUCCESS);
//            return count;
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
