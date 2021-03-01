package com.gsshop.controller;

import com.gsshop.beans.UserBean;
import com.gsshop.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userValidator")
    private Validator userValidator;

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }
    @GetMapping("/join")
    public String join(@ModelAttribute("joinUserBean") UserBean userBean){
        return "user/join";
    }

    @PostMapping("/join_pro")
    public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean userBean, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("HYUNSOO LOG POINT AAA");
            return "user/join";
        }
        return "user/join_success";
    }

    @GetMapping("/modify")
    public String modify(){
        return "user/modify";
    }

    @GetMapping("/logout")
    public String logout(){
        return "user/logout";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(userValidator);
    }

}
