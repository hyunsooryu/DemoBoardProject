package com.gsshop.config;

import com.gsshop.beans.UserBean;
import com.gsshop.beans.ValidateTestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.annotation.SessionScope;

@Import({
        DataBaseAppContext.class,
        ContextSqlMapper.class,
        CacheAppContext.class
})
@ComponentScan(value = {"com.gsshop.service", "com.gsshop.advisor","com.gsshop.cache", "com.gsshop.dao"})
@EnableAspectJAutoProxy
public class RootAppContext {
    //세션 관리를 하겠습니다.
    @Bean("loginUserBean")
    @SessionScope
    public UserBean loginUserBean(){
        return new UserBean();
    }

    @Bean
    @SessionScope
    ValidateTestBean validateTestBean(){return new ValidateTestBean();}
}
