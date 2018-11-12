package com.frame.ssm.common.utils;

import com.frame.ssm.common.constans.SessionConstants;
import com.frame.ssm.domain.User;
import com.frame.ssm.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    private static final Logger logger = Logger.getLogger(SessionUtil.class);


    public static void setSessionKey(HttpSession session,String key,Object value){
        session.setAttribute(key, value);
    }

    public static void setSessionKey(HttpSession session,String key,Object value,int timeout){
        session.setAttribute(key,value);

    }

    public static Object getSessionKey(HttpSession session,String key){
        return session.getAttribute(key);
    }

    public static void destroySessionkey(HttpSession session,String key){
        session.removeAttribute(key);
        session.invalidate();
    }

    /**
     * 清除当前用户的session值
     *
     * @param session
     */
    public static void destroySession(HttpSession session) {
        session.removeAttribute(SessionConstants.SESSION_USER_KEY);
        session.removeAttribute(SessionConstants.SESSION_ROLE_KEY);
        session.removeAttribute(SessionConstants.SESSION_FUNCTION_KEY);
        session.invalidate();
    }

    public static void setMallUserSession(HttpSession session, User user) {
        session.setAttribute(SessionConstants.MALL_USER_KEY, user);
    }

    public static boolean setSessionByNAMEPWD(HttpSession session, String name, String password, HttpServletRequest request) {
        UserService proUserInfoService = ServiceBeanUtil.getBean("userService");
        try {
            User user = proUserInfoService.findUserByUsername(name);
            if(user == null){
                logger.info(name + "不存在或已被删除");
                return false;
            }

            if (!password.equals(MD5Encrypt.md5(user.getPasswd()))) {
                logger.info(name + "密码已被修改");
                return false;
            }

            if(user.getStatusId() == 0){
                logger.info(name + "已失效");
                return false;
            }
            setSessionKey(session,SessionConstants.MALL_USER_KEY,user);
            return true ;
        } catch (Exception e) {
            logger.info("用户名或密码已经修改");
            return false;
        }
    }
}
