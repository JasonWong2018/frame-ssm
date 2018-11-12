package com.frame.ssm.test;

import com.frame.ssm.mq.service.SendMqMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:spring/applicationContext-common.xml","classpath:spring/applicationContext-activemq.xml"})
public class MqTest {

    @Autowired
    private SendMqMessage sendMqMessage;

    @Test
    public void test1(){
        sendMqMessage.send("test.queue","test");
    }
}
