package com.frame.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class ShiroController {

    @RequestMapping("/shiroTest")
    public String shiroTest(){
        return "shiro_role";
    }
}
