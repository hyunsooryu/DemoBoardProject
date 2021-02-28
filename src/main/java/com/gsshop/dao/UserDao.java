package com.gsshop.dao;

import com.gsshop.beans.UserBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    final SqlSessionTemplate sqlSessionTemplate;

    public UserDao(SqlSessionTemplate sqlSessionTemplate) {
        System.out.println("TestService construction hyunsoolog");
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public Optional<UserBean> getUser(String userId){
        System.out.println("DB 조회를 시작합니다.");
        UserBean userBean = sqlSessionTemplate.selectOne("user.get_user", userId);
        return Optional.ofNullable(userBean);
    }


}
