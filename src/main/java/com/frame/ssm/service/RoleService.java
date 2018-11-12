package com.frame.ssm.service;

import com.frame.ssm.domain.Role;
import com.frame.ssm.domain.User;

import java.util.List;

public interface RoleService {
    List<Role> getRoleListByUserId(int userId);

    List<Role> getRoleList();
}
