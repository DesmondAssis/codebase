//package com.desmond.codebase.redis;
//
//import com.dianping.cat.Cat;
//import com.dianping.cat.message.Transaction;
//import com.xish.core.enums.RedisOpEnum;
//import com.xish.core.enums.RedisOpTypeEnum;
//import com.xish.core.util.cat.CatUtil;
//import com.xish.core.vo.redis.ZSetVo;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.ZSetOperations;
//
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Set;
//
//import static com.xish.core.constants.Constants.CACHE_PREFIX;
//
///**
// * Created by zhanghuyi on 16/6/12.
// */
//public class ZSetRedisUtil {
//
//    private static ZSetOperations zSetOperations = RedisUtil.getRedisTemplate().opsForZSet();
//
//    /**
//     * 添加元素到集合，元素在集合中存在则更新对应score
//     *
//     * @param key
//     * @param value
//     * @param score
//     * @return
//     */
//    public static boolean zadd(String key, Object value, double score) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.ZADD.getName());
//        try {
//            boolean result = zSetOperations.add(key, value, score);
//
//            CatUtil.logEvent(RedisOpTypeEnum.SORTEDSET.getName(), key, RedisOpEnum.ZADD.getName());
//            t.setStatus(Transaction.SUCCESS);
//            return result;
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
//     *
//     * @param key
//     * @param values
//     * @return
//     */
//    public static long zrem(String key, Object... values) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.ZREM.getName());
//        try {
//            long lon = zSetOperations.remove(key, values);
//
//            CatUtil.logEvent(RedisOpTypeEnum.SORTEDSET.getName(), key, RedisOpEnum.ZREM.getName());
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
//    public static Object zBatchScore(List<ZSetVo> setVos){
//        return RedisUtil.getRedisTemplate().execute((RedisCallback<Object>) connection -> {
//            connection.openPipeline();
//            for (ZSetVo setVo : setVos) {
//                connection.zScore(RedisUtil.rawKey(setVo.getKey()), RedisUtil.rawValue(setVo.getValue()));
//            }
//            return connection.closePipeline();
//        });
//    }
//
//    public static void zBatchAdd(List<ZSetVo> zSetVos) {
//        RedisUtil.getRedisTemplate().execute((RedisCallback<Object>) connection -> {
//            connection.openPipeline();
//            for (ZSetVo setVo : zSetVos) {
//                connection.zAdd(RedisUtil.rawKey(setVo.getKey()), setVo.getScore(), RedisUtil.rawValue(setVo.getValue()));
//                connection.expire(RedisUtil.rawKey(setVo.getKey()), 7776000L); // 90天
//            }
//            return connection.closePipeline();
//        });
//    }
//
//    public static LinkedHashSet<String> zRevRangeByScore(String key, Double min, Double max) {
//        return (LinkedHashSet<String>) zSetOperations.reverseRangeByScore(key, min, max);
//    }
//
//    public static LinkedHashSet<String> zRevRangeByScore(String key, Double min, Double max, int offset, int limit) {
//        return (LinkedHashSet<String>) zSetOperations.reverseRangeByScore(key, min, max, offset, limit);
//    }
//
//    public static LinkedHashSet<String> zRevRange(String key, long start, long end) {
//        return (LinkedHashSet<String>) zSetOperations.reverseRange(key, start, end);
//    }
//
//    public static Set zrange(String key, long start, long stop) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.ZRANGE.getName());
//        try {
//            Set set = zSetOperations.range(key, start, stop);
//
//            CatUtil.logEvent(RedisOpTypeEnum.SORTEDSET.getName(), key, RedisOpEnum.ZRANGE.getName());
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
//    public static Double zscore(String key, Object value) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.ZSCORE.getName());
//        try {
//            Double score = zSetOperations.score(key, value);
//
//            CatUtil.logEvent(RedisOpTypeEnum.SORTEDSET.getName(), key, RedisOpEnum.ZSCORE.getName());
//            t.setStatus(Transaction.SUCCESS);
//            return score;
//        } catch (RuntimeException e) {
//            t.setStatus(e);
//            Cat.logError(e);
//            throw new RuntimeException(e);
//        } finally {
//            t.complete();
//        }
//    }
//
//    public static long zcard(String key) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.ZCARD.getName());
//        try {
//            long count = zSetOperations.zCard(key);
//
//            CatUtil.logEvent(RedisOpTypeEnum.SORTEDSET.getName(), key, RedisOpEnum.ZCARD.getName());
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
//}
