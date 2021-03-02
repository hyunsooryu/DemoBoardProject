package com.gsshop.service;

import com.gsshop.beans.UserBean;
import com.gsshop.config.RootAppContext;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringJUnitConfig(RootAppContext.class)
class UserServiceTest {

    UserService userService;

    @Autowired
    UserServiceTest(UserService userService){
        this.userService = userService;
    }

    @Test
    void injectionTest(){
        assertNotNull(userService);
    }

    @Test
    void checkIdAlreadyExist(){
        assertAll(()->{
            assertFalse(userService.checkIdAlreadyExist("kky"));
        },
                ()->{
            assertTrue(userService.checkIdAlreadyExist("blessdutch"));
        }
        );
    }
    @Test
    void addUserInfoTest(){
        UserBean testUserBean = UserBean.builder()
                .userId("test555")
                .userName("한지민여왕님")
                .userPw("100825asa")
                .build();
        userService.addUserInfo(testUserBean);
    }
}