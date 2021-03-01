package com.gsshop.controller;

import com.gsshop.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AjaxController {

    private final UserService userService;

    public AjaxController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/user/{userId}", produces = "application/json; charset=UTF-8")
    public String checkUserId(@PathVariable("userId") String userId){
       String result =  userService.checkIdAlreadyExist(userId) ? "Y" : "N";
       return result;
    }

}
