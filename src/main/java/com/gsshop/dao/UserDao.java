package com.gsshop.dao;

import com.gsshop.beans.UserBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    final SqlSessionTemplate sqlSessionTemplate;

    public UserDao(SqlSessionTemplate sqlSessionTemplate) {
        System.out.println("TestService construction hyunsoolog");
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public Optional<List<UserBean>> getUserList(){
        List<UserBean> userBeanList = sqlSessionTemplate.selectList("user.get_user_list");
        return Optional.ofNullable(userBeanList);
    }

    public Optional<UserBean> getUser(String userId){
        System.out.println("DB 조회를 시작합니다.");
        UserBean userBean = sqlSessionTemplate.selectOne("user.get_user", userId);
        return Optional.ofNullable(userBean);
    }

    public void addUserInfo(UserBean joinUserBean){
        sqlSessionTemplate.insert("user.add_user_info", joinUserBean);
    }

    public Optional<UserBean> getLoginUserInfo(UserBean tmpLoginUserBean){
        UserBean tmpBean = sqlSessionTemplate.selectOne("user.get_login_user_info", tmpLoginUserBean);
        return Optional.ofNullable(tmpBean);
    }

    public Optional<UserBean> getModifyUserInfo(int userIdx){
        UserBean tmpBean = sqlSessionTemplate.selectOne("user.get_modify_user_info", userIdx);
        return Optional.ofNullable(tmpBean);
    }

    public void updateModifyUserInfo(HashMap<String, Object> criteria){
        sqlSessionTemplate.update("user.update_modify_user_info", criteria);
    }



}
