package com.gsshop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test(HttpServletRequest request, ModelMap model){
        Map<String, Object> map = new HashMap<>();
        map.put("hyunsoo","awesome");
        map.put("yongho","handsome");
        map.put("data", request.getAttribute("data"));
        model.addAllAttributes(map);
        return "test";
    }
}
