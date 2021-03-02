package com.gsshop.service;

import com.gsshop.beans.UserBean;
import com.gsshop.dao.UserDao;
import org.springframework.stereotype.Service;

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
}
