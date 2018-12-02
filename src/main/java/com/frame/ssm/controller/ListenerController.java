package com.frame.ssm.controller;

import com.frame.ssm.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/listener")
public class ListenerController {

    @RequestMapping("/init")
    @ResponseBody
    public String init(HttpServletRequest request){
        String initData = request.getServletContext().getAttribute("initData").toString();
        System.out.println(initData);

        Person p = new Person();
        p.setUsername("jerry");
        p.setPassword("123");
        request.getSession().setAttribute("person",p);
        return initData;
    }
}
