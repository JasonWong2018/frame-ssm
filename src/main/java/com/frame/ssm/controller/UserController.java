package com.frame.ssm.controller;

import com.frame.ssm.domain.User;
import com.frame.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/userList")
    public String userList(HttpServletRequest request){

        try{
            List<User> userList = userService.findUserList();
            request.setAttribute("userList",userList);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return "page/user/user_list";
    }
}
