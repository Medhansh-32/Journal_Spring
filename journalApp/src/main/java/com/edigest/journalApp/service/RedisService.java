package com.edigest.journalApp.service;

import com.edigest.journalApp.apiresponse.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public<T> T get(String key,Class<T> entityClass){
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(entityClass);
           return mapper.readValue(o.toString(),entityClass);

        }catch (Exception e){
             log.error("Exception occured");
             return null;
        }
    }
    public<T> void set(String key,Object o,Long ttl){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(o);
            System.out.println(o);
            redisTemplate.opsForValue().set(key,json.toString(),ttl, TimeUnit.SECONDS);
        }catch (Exception e){
            log.error("Exception occured");

        }
    }
}
