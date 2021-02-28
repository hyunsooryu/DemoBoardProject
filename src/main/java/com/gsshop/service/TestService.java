package com.gsshop.service;

import com.gsshop.annotations.RedisCacheFirst;
import com.gsshop.beans.UserBean;
import com.gsshop.dao.UserDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    final SqlSessionTemplate sqlSessionTemplate;

    final UserDao userDao;

    public TestService(SqlSessionTemplate sqlSessionTemplate, UserDao userDao) {
        System.out.println("TestService construction hyunsoolog");
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.userDao = userDao;
    }

    List<UserBean> getUserList(){
        List<UserBean> userBeanList = sqlSessionTemplate.selectList("user.get_user_list");
        return userBeanList;
    }

    @RedisCacheFirst
    UserBean getUser(String userId){
        UserBean userBean = userDao.getUser(userId).orElse(null);
        return userBean;
    }
}
