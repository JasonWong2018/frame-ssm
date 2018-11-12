package com.frame.ssm.test;


import com.frame.ssm.common.utils.FastJsonUtil;
import com.frame.ssm.domain.User;
import com.frame.ssm.mapper.UserMapper;
import com.github.abel533.entity.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 通用mapper使用
 */
@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:spring/applicationContext-common.xml"})
public class MappterTest {

    @Autowired
    private UserMapper userMapper;

    /*@Test
    public void test1(){
        User user = new User();
        user.setAge(18);
        List<User> userList = userMapper.select(user);
        System.out.println(FastJsonUtil.convertObjectToJSON(userList));
    }

    @Test
    public void test2(){
        User user = new User();
        user.setName("jack");
        user = userMapper.selectOne(user);
    }

    @Test
    public void test3(){
        User user = userMapper.selectByPrimaryKey(1);
    }

    @Test
    public void test4(){
        User user = new User();
        user.setAge(18);
        int i = userMapper.selectCount(user);
    }

    @Test
    public void test5(){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",1);
        List<User> users = userMapper.selectByExample(example);
    }

    @Test
    public void test6(){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("age",18);
        int i = userMapper.selectCountByExample(example);
    }

    @Test
    public void test7(){
        User user = new User();
        user.setId(1);
        user.setName("test7");
        int i = userMapper.updateByPrimaryKey(user);
    }

    @Test
    public void test8(){
        User user = new User();
        user.setId(2);
        user.setName("test8");
        int i = userMapper.updateByPrimaryKeySelective(user);
        System.out.println(i);
    }

    @Test
    public void test9(){
        User user = new User();
        user.setName("123");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void test10(){
        User user = new User();
        user.setName("123");
        int i = userMapper.insertSelective(user);
        System.out.println(i);
        System.out.println(FastJsonUtil.convertObjectToJSON(user));
    }

    @Test
    public void test11(){
        int i = userMapper.deleteByPrimaryKey(4);
        System.out.println(i);
    }

    @Test
    public void test12(){
        User user = new User();
        user.setName("test8");
        user.setAge(18);
        userMapper.delete(user);
    }

    @Test
    public void test13(){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name","tom");
        criteria.andEqualTo("age",19);
        userMapper.deleteByExample(example);
    }*/

}
