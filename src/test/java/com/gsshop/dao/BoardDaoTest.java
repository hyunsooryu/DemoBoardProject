package com.gsshop.dao;

import com.gsshop.beans.BoardInfoBean;
import com.gsshop.beans.UserBean;
import com.gsshop.config.RootAppContext;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringJUnitConfig(RootAppContext.class)
class BoardDaoTest {

    final BoardDao boardDao;

    final UserDao userDao;

    @Autowired
    BoardDaoTest(BoardDao boardDao, UserDao userDao){
        this.boardDao = boardDao;
        this.userDao = userDao;
    }


    @Test
    void injection_test(){
        assertNotNull(boardDao);
        assertNotNull(userDao);
    }

    /*
        BoardInfoBean을 불러오는 쿼리 테스트입니다.
        값이 있다면 출력을 해줍니다.
     */
    @Test
    void get_board_info_list(){
       boardDao.getBoardInfoList()
               .ifPresent((boardInfoBeanList)->{
                 boardInfoBeanList.stream()
                    .forEach(boardInfoBean->{
                        System.out.println(boardInfoBean);
                    });
               });
    }

    @Test
    void get_login_user_info(){
        assertNotNull(userDao.getLoginUserInfo(UserBean.builder().userId("blessdutch").userPw("100825aas").build())
                .orElse(null));
    }
}