package com.gsshop.config;


import com.gsshop.beans.BoardInfoBean;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@PropertySource(value = "classpath:/properties/${spring.profiles.active}_db.properties")
public class DataBaseAppContext {
    @Value("${db.classname}")
    private String dbClassName;
    @Value("${db.url}")
    private String dbUrl;
    @Value("${db.username}")
    private String dbUsername;
    @Value("${db.password}")
    private String dbPassword;

    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(dbClassName);
        source.setUrl(dbUrl);
        source.setUsername(dbUsername);
        source.setPassword(dbPassword);
        return source;
    }

    //트랜잭션 메니저 등록
    @Bean
    public DataSourceTransactionManager transactionManager(BasicDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }


}
