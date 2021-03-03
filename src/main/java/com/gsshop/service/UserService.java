package com.gsshop.service;

import com.gsshop.beans.UserBean;
import com.gsshop.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public void getLoginUserInfo(UserBean tmpLoginUserBean, UserBean sessionBean) {
        userDao.getLoginUserInfo(tmpLoginUserBean)
                .ifPresent((successLoginBean)->{
                    sessionBean.setUserIdx(successLoginBean.getUserIdx());
                    sessionBean.setUserName(successLoginBean.getUserName());
                    sessionBean.setUserLogin(true);
                });
    }
}
