package com.imooc.miaoshaproject.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.redis.core.RedisTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.imooc.miaoshaproject.App.class})// 指定启动类
public class CacheServiceImplTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void getFromCommonCache() {
        System.out.println(redisTemplate.getHashKeySerializer());
    }

}