package com.gsshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @GetMapping({"/", "/main"})
    public String main(HttpServletRequest request){
        System.out.println(request.getServletContext().getRealPath("/"));
        return "main";
    }
}
