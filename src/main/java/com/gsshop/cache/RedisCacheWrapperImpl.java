package com.gsshop.cache;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;


public class RedisCacheWrapperImpl<O> implements RedisCacheWrapper<O>{

    private RedisTemplate<String, Object> redisTemplate;

    private ValueOperations<String, Object> redisCache;

    @Getter
    private String prefix;

    public RedisCacheWrapperImpl(RedisTemplate<String, Object> redisTemplate, String prefix){
        System.out.println("RedisCacheWrapperImpl이 생성되었습니다.");
        this.redisTemplate = redisTemplate;
        this.redisCache = this.redisTemplate.opsForValue();
        this.prefix = prefix;
    }

    @Override
    public Optional<O> getFromCache(String e) {
        if(!StringUtils.hasLength(e)){
            return Optional.empty();
        }
        O data = ((Supplier<O>) () -> {
            O tmpData = null;
            try {
                tmpData = (O) redisCache.get(prefix + e);
            } catch (Exception exe) {
                exe.printStackTrace();
            }
            return tmpData;
        }).get();
        Optional<O> optional = data == null? Optional.empty() : Optional.of(data);
        return optional;
    }

    @Override
    public void setIntoCache(String key, O o){
        if(!StringUtils.hasLength(key)){
            return;
        }
        try{
            redisCache.set(prefix + key, o);
        }catch (Exception exe){
            exe.printStackTrace();
        }
    }
}
