package com.gsshop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Import({
        DataBaseAppContext.class,
        ContextSqlMapper.class,
        CacheAppContext.class
})
@ComponentScan(value = {"com.gsshop.service", "com.gsshop.advisor","com.gsshop.cache", "com.gsshop.dao"})
@EnableAspectJAutoProxy
public class RootAppContext {
}
