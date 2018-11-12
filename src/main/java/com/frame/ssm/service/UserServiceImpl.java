package com.frame.ssm.service;

import com.frame.ssm.domain.User;
import com.frame.ssm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findUserList() {
        return userMapper.select(null);
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public User getUserById(int id) {
        User user = new User();
        user.setId(id);
        return userMapper.selectOne(user);
    }

    public User findUserByUsername(String userName) {
        User user = new User();
        user.setUsername(userName);
        return userMapper.selectOne(user);
    }
}
