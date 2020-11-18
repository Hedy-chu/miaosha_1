package com.miaosha_1.service;

import com.alibaba.fastjson.JSON;
import com.miaosha_1.redis.KeyPrefix;
import com.miaosha_1.redis.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author qqtang
 * @Date 2020/11/16 9:40
 * @Desc
 */
@Service
public class RedisService {
    @Autowired
    RedisConfig redisConfig;
    @Autowired
    JedisPool jedisPool;
    /*
    获取对象
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            String str = jedis.get(realKey);
            T t = stringToBean(str,clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    /*
    获取对象
     */
    public <T> Boolean set(KeyPrefix prefix,String key,T value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length()<=0){
                return false;
            }
            String realKey = prefix.getPrefix()+key;
            int expireSecond = prefix.expireSecond();
            if (expireSecond<=0){
                jedis.set(realKey ,str);
            }else {
                //有有效期的set：setex
                jedis.setex(realKey,expireSecond,str);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }

    }

    /*
    判断是否存在
     */
    public <T> Boolean exists(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            Boolean exists = jedis.exists(realKey);
            return exists;
        } finally {
            returnToPool(jedis);
        }
    }

    /*
    增加值
     */
    public <T> Long incr(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            Long incr = jedis.incr(realKey);
            return incr;
        } finally {
            returnToPool(jedis);
        }
    }

    /*
    减少值
     */
    public <T> Long decr(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            Long decr = jedis.decr(realKey);
            return decr;
        } finally {
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        if (value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class){
            return ""+value;
        }else if (clazz == String.class){
            return (String) value;
        }else if (clazz == long.class || clazz == Long.class){
            return ""+value;
        }else {
            return JSON.toJSONString(value);
        }
    }

    private <T> T stringToBean(String str,Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null){
            return null;
        }
        if (clazz == int.class || clazz == Integer.class){
            return (T)Integer.valueOf(str);
        }else if (clazz == String.class){
            return (T)str;
        }else if (clazz == long.class || clazz == Long.class){
            return (T)Long.valueOf(str);
        }else{
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null){
            jedis.close();
        }
    }

    @Bean
    public JedisPool JedisPoolFortory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        jedisPoolConfig.setMaxIdle(redisConfig.getPoolMaxldle());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait()*1000);
        System.out.println(jedisPoolConfig.getMaxTotal());
        System.out.println(jedisPoolConfig.getMaxIdle());
        System.out.println(jedisPoolConfig.getMaxWaitMillis());
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getTimeout() * 1000);
        return jedisPool;
    }
}
