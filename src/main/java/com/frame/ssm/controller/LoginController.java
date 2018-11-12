package com.frame.ssm.controller;

import com.frame.ssm.common.base.BaseController;
import com.frame.ssm.common.constans.Constant;
import com.frame.ssm.common.utils.CookieUtils;
import com.frame.ssm.common.utils.MD5Encrypt;
import com.frame.ssm.common.utils.SessionUtil;
import com.frame.ssm.domain.User;
import com.frame.ssm.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/shiroLogin")
    @ResponseBody
    public String shiroLogin(User user,HttpServletRequest request,HttpServletResponse response){
        try {
            if (StringUtils.isBlank(user.getUsername())) {
                return "账户不能为空";
            }
            if (StringUtils.isBlank(user.getPasswd())) {
                return "请输入密码";
            }
            String validCode = request.getParameter("validCode");
            if (StringUtils.isEmpty(validCode)) {
                return "验证码不能为空";
            }
            String code = (String) request.getSession().getAttribute("code");
            if (!validCode.equalsIgnoreCase(code)) {
                return "验证码错误";
            }
            User loginUser = userService.findUserByUsername(user.getUsername().trim());
            if (null == loginUser) {
                return "账户错误";
            }
            if (!loginUser.getPasswd().equalsIgnoreCase(MD5Encrypt.md5(user.getPasswd()))) {
                return "密码错误";
            }

            Subject subject = SecurityUtils.getSubject();
            // 把用户名和密码封装为UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),MD5Encrypt.md5(user.getPasswd()).toUpperCase());
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                return "用户名或密码错误";
            }
            if (subject.isAuthenticated()) {
                String online = request.getParameter("online") == null ? "" : request.getParameter("online");
                // 设定COOKIE值
                if ("is".equals(online)) {// 记住密码自动登录
                    CookieUtils.setCookie(response, loginUser, Constant.COOKIE_EXPIRY);
                } else {
                    CookieUtils.clearCookieValue(response);
                }
                return SUCCESS;
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return "系统异常";
        }
        return SUCCESS;
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        return "index";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isBlank(user.getUsername())) {
                return "账户不能为空";
            }
            if (StringUtils.isBlank(user.getPasswd())) {
                return "请输入密码";
            }
            String validCode = request.getParameter("validCode");
            if (StringUtils.isEmpty(validCode)) {
                return "验证码不能为空";
            }
            String code = (String) request.getSession().getAttribute("code");
            if (!validCode.equalsIgnoreCase(code)) {
                return "验证码错误";
            }
            User loginUser = userService.findUserByUsername(user.getUsername().trim());
            if (null == loginUser) {
                return "账户错误";
            }
            if (!loginUser.getPasswd().equalsIgnoreCase(MD5Encrypt.md5(user.getPasswd()))) {
                return "密码错误";
            }

            String online = request.getParameter("online") == null ? "" : request.getParameter("online");
            // 设定COOKIE值
            if ("is".equals(online)) {// 记住密码自动登录
                CookieUtils.setCookie(response, loginUser, Constant.COOKIE_EXPIRY);
            } else {
                CookieUtils.clearCookieValue(response);
            }
            return SUCCESS;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "系统异常";
        }
    }
}
