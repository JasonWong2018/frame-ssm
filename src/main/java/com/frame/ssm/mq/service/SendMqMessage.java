package com.frame.ssm.mq.service;

public interface SendMqMessage {

	void send(String queueName, String message);
	
	/**
	 * 发送一条消息到指定的队列（目标）
	 * @param queueName  队列名称
	 * @param message 消息内容
	 * @param level 消息优先级
	 * @param time 消息有效期
	 */
	public void send(String queueName, String message, int level, long time);
	
	/**
	 * 延迟发送消息
	 * @param queueName
	 * @param message
	 * @param delay
	 */
	public void sendDelayMessage(String queueName, String message, long delay);
	
}
