package com.frame.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "HELLO";
    }

}
