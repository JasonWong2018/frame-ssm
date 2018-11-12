package com.frame.ssm.realm;

import com.frame.ssm.domain.Function;
import com.frame.ssm.domain.Role;
import com.frame.ssm.domain.User;
import com.frame.ssm.service.FunctionService;
import com.frame.ssm.service.RoleService;
import com.frame.ssm.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    // 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("shiroRealm");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private FunctionService functionService;


    // 用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();

        User user = userService.findUserByUsername(username);
        final SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //为用户授权,只需将用户的权限添加到info即可
        //如果是超级管理员
        if (user.getUsername().equals("superadmin")) {
            // 查询出所有的角色，给认证信息对象
            List<Role> roleList = roleService.getRoleList();
            for (Role role : roleList) {
                authorizationInfo.addRole(role.getCode());
            }

            //查询出所有功能菜单
            List<Function> functionList = functionService.getFunctionList();
            for (Function function : functionList) {
                authorizationInfo.addStringPermission(function.getName());
            }
        } else {
            //普通用户
            List<Role> roleList = roleService.getRoleListByUserId(user.getId());
            for (Role role : roleList) {
                authorizationInfo.addRole(role.getCode());
                //导航查询,获取某角色的拥有的功能权限
                List<Function> functions = role.getFunctions();
                for (Function function : functions) {
                    authorizationInfo.addStringPermission(function.getName());
                }
            }
        }
        //将授权信息交给安全管理器接口。
        return authorizationInfo;
    }

    //用于用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws
            AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 通过表单接收的用户名
        String username = token.getUsername();
        if (StringUtils.isNoneBlank(username)) {
            User sysUser = userService.findUserByUsername(username);
            if (sysUser != null) {
                return new SimpleAuthenticationInfo(sysUser.getUsername(), sysUser.getPasswd(), getName());
            }
        }
        return null;
    }

    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

    //上传测试
}
