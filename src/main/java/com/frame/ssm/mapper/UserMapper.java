package com.frame.ssm.mapper;

import com.frame.ssm.domain.User;
import com.github.abel533.mapper.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userMapper")
public interface UserMapper extends Mapper<User> {
    List<User> findUserList();

    @Insert(value = " insert into user(name) values(#{name})")
    void insertUser(User user);
}
