package com.gsshop.controller;

import com.gsshop.config.RootAppContext;
import com.gsshop.config.ServletAppContext;
import com.gsshop.config.SpringConfig;
import com.gsshop.config.filter.hyunsooFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/*
    컨트롤러를 테스트 하는 테스트 클래스
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringJUnitWebConfig(classes = {SpringConfig.class, ServletAppContext.class, RootAppContext.class})
public class ControllerTest {
    WebApplicationContext context;

    MockMvc mockMvc;

    @Autowired
    ControllerTest(WebApplicationContext context){
        this.context = context;
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(
                new CharacterEncodingFilter("UTF-8")
        ).addFilter(new hyunsooFilter())
                .build();
    }

    @Test
    @DisplayName(value="ApplicationContext의 주입을 확인하는 테스트입니다.")
    void test_context_injection(){
        assertNotNull(context);
    }


    @DisplayName(value="TestController -> test 를 확인하는 테스트입니다.")
    void test_testController_test() throws Exception {
       MvcResult mvcResult =  mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
       assertAll(
               ()->{
                   assertNull(mvcResult.getModelAndView().getModel());
                   },
               ()->{
                   assertNotNull(mvcResult.getModelAndView().getModel().get("hyunsoo"));
                   }
       );
    }

    @Test
    @DisplayName(value="UserTestController -> login을 확인하는 테스트입니다.")
    void user_testController_login() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/login"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName(value="UserTestController -> join을 확인하는 테스트입니다.")
    void user_testController_join() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/login"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName(value="UserTestController -> modify를 확인하는 테스트입니다.")
    void user_testController_modify() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/modify"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName(value="UserTestController -> logout을 확인하는 테스트입니다.")
    void user_testController_logout() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/logout"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName(value="BoardController -> main을 확인하는 테스트입니다.")
    void board_controller_main() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/board/main"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName(value="BoardController -> read을 확인하는 테스트입니다.")
    void board_controller_read() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/board/read"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName(value="BoardController -> read을 확인하는 테스트입니다.")
    void board_controller_board_info_list_api() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/board/topMenuList"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName(value = "AjaxController -> id의 유효성을 확인하는 테스트입니다.")
    void ajax_controller_user_id_test() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }


}
