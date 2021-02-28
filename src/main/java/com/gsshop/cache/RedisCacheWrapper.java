package com.gsshop.cache;

import java.util.Optional;

public interface RedisCacheWrapper<O> {
    public Optional<O> getFromCache(String e);

    public void setIntoCache(String key, O o);

    public String getPrefix();

}
