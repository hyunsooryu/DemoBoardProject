package com.gsshop.service;

import com.gsshop.beans.UserBean;
import com.gsshop.config.RootAppContext;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringJUnitConfig(value = {RootAppContext.class})
class TestServiceTest {

    private TestService testService;

    @Autowired
    TestServiceTest(TestService testService){
        this.testService = testService;
    }

    @Test
    void testInjection(){
        assertNotNull(testService);
    }

    @Test
    void getUserList(){
        List<UserBean> userBeanList = testService.getUserList();
        assertNotNull(userBeanList);
        userBeanList.stream().forEach(
                userBean->System.out.println(userBean)
        );
    }

    @Test
    void getUser(){
        UserBean userBean = testService.getUser("fff");
        assertNull(userBean);
        System.out.println(userBean);
    }

    @Test
    void testOptionalStream(){
        assertTrue(Optional.ofNullable(testService.getUser("blessdutch"))
                .map(UserBean::getUserName)
                .filter("류현수"::equals)
                .isPresent());
    }

    @Test
    void testNull(){
        String data = null;
        Optional<String> tmpData = Optional.ofNullable("HYUNSOO");
        System.out.println(tmpData.isPresent());
    }

}