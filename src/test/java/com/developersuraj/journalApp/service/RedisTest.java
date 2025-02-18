package com.developersuraj.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<Object , Object> redisTemplate;

    @Test
    void test(){
        redisTemplate.opsForValue().set("email", "abc@gmail.com");

        Object salary = redisTemplate.opsForValue().get("salary");

        int a = 1;

    }

}
