package com.miaosha_1.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author qqtang
 * @Date 2020/11/16 9:27
 * @Desc
 */
@Component
@Getter
@Setter
public class RedisConfig {
    @Value(value ="${spring.redis.host}")
    private String host;
    @Value(value ="${spring.redis.port}")
    private int port;
    @Value(value ="${spring.redis.timeout}")
    private int timeout;
    private String password;
    @Value(value ="${spring.redis.pool.MaxTotal}")
    private int poolMaxTotal;
    @Value(value ="${spring.redis.pool.max-idle}")
    private int poolMaxldle;
    @Value(value ="${spring.redis.pool.max-wait}")
    private int poolMaxWait;
}
