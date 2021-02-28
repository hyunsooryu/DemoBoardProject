package com.gsshop.config;

import com.gsshop.beans.BoardInfoBean;
import com.gsshop.beans.UserBean;
import com.gsshop.cache.RedisCacheWrapper;
import com.gsshop.cache.RedisCacheWrapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.List;

@Configuration
@PropertySource(value = {"classpath:/properties/${spring.profiles.active}_cache.properties"})
public class CacheAppContext {
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    public JedisConnectionFactory connectionFactory(JedisPoolConfig jedisPoolConfig) {
        System.out.println("hyunsoo log : " + redisHost);
        System.out.println("hyunsoo log : " + redisPort);
        System.out.println("connectionFactory만드는중입니다.");
        System.out.println("jedisPoolConfig : " + jedisPoolConfig);
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(redisHost);
        connectionFactory.setPort(redisPort);
        // connectionFactory.setTimeout(Duration.ofSeconds(60L).);
        connectionFactory.setPoolConfig(jedisPoolConfig);
        return connectionFactory;
    }

    //CONFIG SET protected-mode no 일때 캐시 접근이 제한됨

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory connectionFactory) {
        System.out.println("redisTemplate만드는 중입니다.");
        System.out.println(connectionFactory + "주입되었습니다.");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(2000);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(36);
        poolConfig.setMaxWaitMillis(1000);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(600).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(300).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }

    @Bean
    public RedisCacheWrapper<UserBean> userBeanRedisCacheWrapper(RedisTemplate redisTemplate){
        System.out.println("UserBean Redis Cache");
        System.out.println("RedisCacheWrapper Bean 주입을 시작합니다.");
        RedisCacheWrapper<UserBean> userBeanRedisCacheWrapper = new RedisCacheWrapperImpl<UserBean>(redisTemplate, "userBeanTest");
        return userBeanRedisCacheWrapper;
    }

    @Bean
    public RedisCacheWrapper<List<BoardInfoBean>> boardInfoBeanRedisCacheWrapper(RedisTemplate redisTemplate){
        System.out.println("BoardInfoBean Redis Cache");
        System.out.println("RedisCacheWrapper Bean 주입을 시작합니다.");
        RedisCacheWrapper<List<BoardInfoBean>> boardInfoBeanRedisCacheWrapper = new RedisCacheWrapperImpl<List<BoardInfoBean>>(redisTemplate, "boardInfoBeanTest");
        return boardInfoBeanRedisCacheWrapper;
    }



}
