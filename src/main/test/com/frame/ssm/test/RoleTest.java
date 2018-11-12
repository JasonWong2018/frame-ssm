package com.frame.ssm.test;

import com.frame.ssm.common.utils.FastJsonUtil;
import com.frame.ssm.domain.Role;
import com.frame.ssm.mapper.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:spring/applicationContext-common.xml"})
public class RoleTest {

    @Autowired
    private RoleMapper roleMapper;


    @Test
    public void test1(){
        List<Role> roleListByUserId = roleMapper.getRoleListByUserId(10086);
        System.out.println(FastJsonUtil.convertObjectToJSON(roleListByUserId));
    }
    
    @Test
    public  void test2(){
        Role role = new Role();
        role.setStatus(1);
        List<Role> select = roleMapper.select(role);
    }


}
