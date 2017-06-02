//package com.desmond.codebase.redis;
//
//import com.dianping.cat.Cat;
//import com.dianping.cat.message.Transaction;
//import com.xish.core.enums.RedisInstanceEnum;
//import com.xish.core.enums.RedisOpEnum;
//import com.xish.core.enums.RedisOpTypeEnum;
//import com.xish.core.util.JsonConverterUtil;
//import com.xish.core.util.cat.CatUtil;
//import org.springframework.data.redis.core.HashOperations;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import static com.xish.core.constants.Constants.*;
//import static com.xish.core.constants.Constants.CACHE_PREFIX;
//
///**
// * Created by zhanghuyi on 16/6/12.
// */
//public class HashRedisUtil {
//
//    private static HashOperations hashOperations = RedisUtil.getRedisTemplate().opsForHash();
//    private static HashOperations hashOperationsDatacenter = RedisUtil.getRedisTemplateDataCenter().opsForHash();
//
//    /**
//     * 设置hash field为指定值，如果key不存在，则先创建
//     * @param key
//     * @param hashKey
//     * @param value
//     * @return
//     */
//    public static void hset(String key, String hashKey, Object value) {
//        hashOperations.put(key, hashKey, value);
//    }
//
//    public static void hset(RedisInstanceEnum ins, String key, String hashKey, Object value) {
//        dispatchRedis(ins).put(key, hashKey, value);
//    }
//
//    /**
//     * 同时设置hash的多个field
//     * @param key
//     * @param map
//     */
//    @SuppressWarnings("Duplicates")
//    public static void hmset(RedisInstanceEnum ins, String key, Map<String, Object> map){
//        dispatchRedis(ins).putAll(key, map);
//    }
//
//    public static void hmset(String key, Map<String, Object> map){
//        hmset(RedisInstanceEnum.DEFAULT, key, map);
//    }
//
//    /**
//     * 同时设置hash的多个field
//     * @param key
//     * @param map
//     */
//    public static void hmset(String key, Map<String, Object> map, long seconds){
//        hmset(key, map);
//        RedisUtil.expire(key, seconds, TimeUnit.SECONDS);
//    }
//
//    public static void hmset(String key, Map<String, Object> map, long time, TimeUnit timeUnit){
//        hmset(key, map);
//        RedisUtil.expire(key, time, timeUnit);
//    }
//
//    public static void hmset(RedisInstanceEnum ins, String key, Map<String, Object> map, long time, TimeUnit timeUnit){
//        hmset(ins, key, map);
//        RedisUtil.expire(ins, key, time, timeUnit);
//    }
//
//    public static void hmset(String key, Object obj, long seconds){
//        Map map = JsonConverterUtil.objectToObject(obj, Map.class);
//        hmset(key, map, seconds);
//        RedisUtil.expire(key, seconds, TimeUnit.SECONDS);
//    }
//
//    @SuppressWarnings("Duplicates")
//    public static void hmset(String key, Object obj, long time, TimeUnit timeUnit){
//        Map map = JsonConverterUtil.objectToObject(obj, Map.class);
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.HMSET.getName());
//        try {
//            hashOperations.putAll(key, map);
//            CatUtil.logEvent(RedisOpTypeEnum.HASH.getName(), key, RedisOpEnum.HMSET.getName());
//            t.setStatus(Transaction.SUCCESS);
//        } catch (Exception e) {
//            t.setStatus(e);
//            Cat.logError(e);
//        } finally {
//            t.complete();
//        }
//        RedisUtil.expire(key, time, timeUnit);
//    }
//
//    /**
//     * 获取指定的hash field
//     * @param key
//     * @param hashKey
//     * @return
//     */
//    public static Object hget(String key, String hashKey) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.HGET.getName());
//        try {
//            Object result = hashOperations.get(key, hashKey);
//
//            CatUtil.logEvent(RedisOpTypeEnum.HASH.getName(), key + " " + hashKey, RedisOpEnum.HGET.getName());
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
//    public static Object hget(RedisInstanceEnum ins, String key, String hashKey) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.HGET.getName());
//        try {
//            Object result = dispatchRedis(ins).get(key, hashKey);
//
//            CatUtil.logEvent(RedisOpTypeEnum.HASH.getName(), key + " " + hashKey, RedisOpEnum.HGET.getName());
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
//     * 返回hash的所有filed和value
//     * @param key
//     * @return
//     */
//    public static Map hgetall(String key) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.HGETALL.getName());
//        Map map = null;
//        try {
//            //老数据会报错，兼容老版本数据
//            map = hashOperations.entries(key);
//
//
//            CatUtil.logEvent(RedisOpTypeEnum.HASH.getName(), key, RedisOpEnum.HGETALL.getName());
//            t.setStatus(Transaction.SUCCESS);
//        } catch (Exception e) {
//            t.setStatus(e);
//        } finally {
//            t.complete();
//        }
//        if (map == null || map.isEmpty()) {
//            return null;
//        }
//        return map;
//    }
//
//    public static Map hgetall(RedisInstanceEnum ins, String key) {
//        Transaction t = Cat.newTransaction(CACHE_PREFIX, RedisOpEnum.HGETALL.getName());
//        Map map = null;
//        try {
//            //老数据会报错，兼容老版本数据
//            map = dispatchRedis(ins).entries(key);
//
//            CatUtil.logEvent(RedisOpTypeEnum.HASH.getName(), key, RedisOpEnum.HGETALL.getName());
//            t.setStatus(Transaction.SUCCESS);
//        } catch (Exception e) {
//            t.setStatus(e);
//        } finally {
//            t.complete();
//        }
//        if (map == null || map.isEmpty()) {
//            return null;
//        }
//        return map;
//    }
//
//    /**
//     * 返回hash的所有filed和value
//     * @param key
//     * @param cls
//     * @param <T>
//     * @return
//     */
//    public static <T> T hgetall(String key, Class<T> cls) {
//        Map map = hgetall(key);
//        if (map == null || map.isEmpty()) {
//            return null;
//        }
//        return JsonConverterUtil.objectToObject(hgetall(key), cls);
//    }
//
//    public static HashOperations dispatchRedis(RedisInstanceEnum redisInstanceEnum) {
//        HashOperations operations = null;
//        switch (redisInstanceEnum) {
//            case DEFAULT:
//                operations = hashOperations;
//                break;
//            case DATA_CENTER:
//                operations = hashOperationsDatacenter;
//                break;
//            default:
//                operations = hashOperations;
//                break;
//        }
//
//        return operations;
//    }
//}
