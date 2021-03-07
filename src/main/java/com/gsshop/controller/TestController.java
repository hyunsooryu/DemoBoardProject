package com.gsshop.controller;


import com.gsshop.beans.UserBean;
import com.gsshop.beans.ValidateTestBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    @GetMapping(value = "/admin")
    public String testUrl(HttpServletRequest request){
        loginUserBean.setUserLogin(true);
        loginUserBean.setUserIdx(1);
        loginUserBean.setUserId("blessdutch");
        loginUserBean.setUserName("류현수");
        return "main";
    }


    @GetMapping(value = "/test", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String test(@Valid @ModelAttribute ValidateTestBean bean, BindingResult result, ModelMap model){
       if(result.hasErrors()){
           System.out.println("ERROR");
           return "ERROR";
       }

       return "GOOD";
    }
}
