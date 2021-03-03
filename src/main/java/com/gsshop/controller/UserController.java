package com.gsshop.controller;

import com.gsshop.beans.UserBean;
import com.gsshop.beans.ValidateTestBean;
import com.gsshop.service.UserService;
import com.gsshop.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    //Session Scope Bean
    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    @Resource(name = "userValidator")
    private UserValidator userValidator;

    private static final String SUCCESS_FLG = "successFlg";

    @Autowired
    ValidateTestBean validateTestBean;

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("tmpLoginUserBean") UserBean tmpLoginUserBean,
                        @RequestParam(name="fail", defaultValue = "false") boolean fail,
                        ModelMap model){
        model.addAttribute("fail", fail);
        return "user/login";
    }

    @PostMapping("/login_pro")
    public String login_pro(@Valid @ModelAttribute("tmpLoginUserBean") UserBean tmpLoginUserBean, BindingResult result, ModelMap model){
        if(result.hasErrors()){
            return "user/login";
        }
        userService.getLoginUserInfo(tmpLoginUserBean, loginUserBean);
        if(!loginUserBean.isUserLogin()){
            model.addAttribute(SUCCESS_FLG, "login_fail");
            return "user/gateway";
        }

        model.addAttribute(SUCCESS_FLG, "login_pro");
        return "user/gateway";
    }

    @GetMapping("/join")
    public String join(@ModelAttribute("joinUserBean") UserBean userBean){
        return "user/join";
    }

    @PostMapping("/join_pro")
    public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean userBean, BindingResult result, ModelMap model){
        if(result.hasErrors()){
            System.out.println("HYUNSOO LOG POINT AAA");
            return "user/join";
        }
        userService.addUserInfo(userBean);
        model.addAttribute(SUCCESS_FLG, "join_pro");
        return "user/gateway";
    }

    @GetMapping("/modify")
    public String modify(){
        return "user/modify";
    }

    @GetMapping("/logout")
    public String logout(ModelMap model){
        loginUserBean.setUserLogin(false);
        model.addAttribute(SUCCESS_FLG, "log_out");
        return "user/gateway";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("INIT-BINDER HYUNSOO LOG");
        binder.addValidators(userValidator);
    }

}
