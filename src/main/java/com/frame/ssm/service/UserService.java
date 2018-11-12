package com.frame.ssm.service;

import com.frame.ssm.domain.User;

import java.util.List;

public interface UserService {
    List<User> findUserList();

    void insertUser(User user);

    User getUserById(int id);

    User findUserByUsername(String userName);
}
