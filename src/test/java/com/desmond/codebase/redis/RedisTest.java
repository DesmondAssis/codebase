package com.desmond.codebase.redis;

import com.desmond.codebase.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Li.Xiaochuan on 16/5/20.
 */
public class RedisTest extends BaseTest{

    @Test
    public void testSet() {
        RedisUtil.set("t1", "t2", 2, TimeUnit.MINUTES);
    }

    @Test
    public void setIfAbsent() {
        String key = "sfkey";
        String value = "value";

        boolean isSuccess = RedisUtil.setIfAbsent(key, value);

        isSuccess = RedisUtil.setIfAbsent(key, value);

        isSuccess = RedisUtil.setIfAbsent(key, value);

        Assert.assertTrue(true);
    }
}
