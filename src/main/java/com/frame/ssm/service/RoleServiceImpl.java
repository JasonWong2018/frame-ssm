package com.frame.ssm.service;

import com.frame.ssm.domain.Role;
import com.frame.ssm.domain.User;
import com.frame.ssm.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getRoleListByUserId(int userId) {
        return roleMapper.getRoleListByUserId(userId);
    }

    public List<Role> getRoleList() {
        Role role = new Role();
        role.setStatus(1);
        return roleMapper.select(role);
    }
}
