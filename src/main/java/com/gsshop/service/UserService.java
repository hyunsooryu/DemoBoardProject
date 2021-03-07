package com.gsshop.service;

import com.gsshop.beans.UserBean;
import com.gsshop.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public boolean checkIdAlreadyExist(String userId){
        return userDao.getUser(userId).isPresent();
    }

    public void addUserInfo(UserBean joinUserBean){
        userDao.addUserInfo(joinUserBean);
    }

    @Transactional
    public void getLoginUserInfo(UserBean tmpLoginUserBean, UserBean sessionBean) {
        userDao.getLoginUserInfo(tmpLoginUserBean)
                .ifPresent((successLoginBean)->{
                    sessionBean.setUserIdx(successLoginBean.getUserIdx());
                    sessionBean.setUserName(successLoginBean.getUserName());
                    sessionBean.setUserLogin(true);
                });
        if(!sessionBean.isUserLogin()){
            //TODO 로그인 3회 실패시 로직 추가 - 쿼리 추가
        }
    }

    public void getModifyUserInfo(UserBean modifyUserBean, UserBean sessionBean){
        userDao.getModifyUserInfo(sessionBean.getUserIdx())
                .ifPresent(tmpModifyUserBean->{
                    modifyUserBean.setUserName(tmpModifyUserBean.getUserName());
                    modifyUserBean.setUserId(tmpModifyUserBean.getUserId());
                });
    }

    public void updateModifyUserInfo(HashMap<String,Object> criteria){
        userDao.updateModifyUserInfo(criteria);
    }
}
