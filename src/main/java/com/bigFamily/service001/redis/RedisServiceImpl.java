package com.bigFamily.service001.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wanjie
 * @description
 * @since 1.0
 **/
@Repository
public class RedisServiceImpl {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 判断key是否过期
     * @param key
     * @return
     */
    public boolean isExpire(String key) {
        return expire(key) > 0?false:true;
    }


    /**
     * 从redis中获取key对应的过期时间;
     * 如果该值有过期时间，就返回相应的过期时间;
     * 如果该值没有设置过期时间，就返回-1;
     * 如果没有该值，就返回-2;
     * @param key
     * @return
     */
    public long expire(String key) {
        return stringRedisTemplate.opsForValue().getOperations().getExpire(key);
    }

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void setexDays(String key, String value,int days) {
        stringRedisTemplate.opsForValue().set(key, value, days, TimeUnit.DAYS);
    }

    public void setexMinutes(String key, String value,int minutes) {
        stringRedisTemplate.opsForValue().set(key, value, minutes, TimeUnit.MINUTES);
    }

    public void setexSeconds(String key, String value,int seconds) {
        stringRedisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public Object get(String key) {
        return this.stringRedisTemplate.opsForValue().get(key);
    }

    public void delete(String key){
        stringRedisTemplate.delete(key);
    }

    public void deleteSet(Set<String> keys){
        stringRedisTemplate.delete(keys);
    }

    public Set<String>  keys(String keyPrefix){
        Set<String> keys = stringRedisTemplate.keys(keyPrefix + "*");
        return keys;
    }

    public ListOperations<String,String> opsForList(){
        ListOperations<String,String> listOps = stringRedisTemplate.opsForList();
        return listOps;
    }
}

