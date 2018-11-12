package com.frame.ssm.mq.service;


import com.frame.ssm.common.base.BaseSendMq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service("sendMqMessage")
public class SendMqMessageImpl extends BaseSendMq implements SendMqMessage{

	@Autowired
	private JmsTemplate jmsQueueTemplate;
	
	/**
	 * 发送一条消息到指定的队列（目标）
	 * @param queueName 队列名称
	 * @param message 消息内容
	 */
	public void send(String queueName,String message){
		super.sendMessage(jmsQueueTemplate, queueName, message);
	}

	/**
	 * 发送一条消息到指定的队列（目标）
	 * @param queueName  队列名称
	 * @param message 消息内容
	 * @param level 消息优先级
	 * @param time 消息有效期
	 */
	public void send(String queueName,String message,int level,long time){
		super.sendMessage(jmsQueueTemplate, queueName, message, level, time);
	}
	
	/**
	 * 延迟发送消息
	 * @param queueName
	 * @param message
	 * @param delay
	 */
	public void sendDelayMessage(String queueName,String message,long delay){
		super.sendDelayMessage(jmsQueueTemplate, queueName, message, delay, 0, 0);
	}

}
