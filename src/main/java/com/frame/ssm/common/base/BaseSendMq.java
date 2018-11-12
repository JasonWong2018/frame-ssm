package com.frame.ssm.common.base;

import com.alibaba.fastjson.JSONObject;
import org.apache.activemq.ScheduledMessage;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.DeliveryMode;
import javax.jms.TextMessage;

public class BaseSendMq {

	private static final int MESSAGE_LEVEL = 4;//默认消息等级

	private static final long DEFAULT_TIME = 0;//默认有效期为永久

	protected static final Logger logger = Logger.getLogger(BaseSendMq.class);

	/**
	 * 普通消息发送-非持久化
	 * @param jmsTemplate 消息数据源
	 * @param destination 消息队列名称
	 * @param message 消息内容
	 */
	protected void sendMessage(JmsTemplate jmsTemplate,final String destination,final String message){
		this.sendMessage(jmsTemplate, destination, message, MESSAGE_LEVEL, DEFAULT_TIME);
	}

	/**
	 * 普通消息发送-非持久化
	 * @param jmsTemplate 消息数据源
	 * @param destination 消息队列名称
	 * @param message 消息内容
	 * @param dfaultTime 消息有效期 0为永久
	 */
	protected void sendMessage(JmsTemplate jmsTemplate,final String destination,final String message,final long dfaultTime){
		this.sendMessage(jmsTemplate, destination, message, MESSAGE_LEVEL, dfaultTime);
	}

	/**
	 * 普通消息发送-非持久化
	 * @param jmsTemplate 消息数据源
	 * @param destination 消息队列名称
	 * @param message 消息内容
	 * @param messLevel 消息优先级 1-4为普通 5-9为紧急
	 */
	protected void sendMessage(JmsTemplate jmsTemplate,final String destination,final String message,final int messLevel){
		this.sendMessage(jmsTemplate, destination, message, messLevel, DEFAULT_TIME);
	}

	/**
	 * 普通消息发送-非持久化
	 * @param jmsTemplate 消息数据源
	 * @param destination 消息队列名称
	 * @param message 消息内容
	 * @param messLevel 消息优先级 1-4为普通 5-9为紧急
	 * @param dfaultTime 消息有效期 0为永久
	 */
	protected void sendMessage(JmsTemplate jmsTemplate,final String destination,final String message,final int messLevel,final long dfaultTime){
		jmsTemplate.send(destination, (session) -> {
			TextMessage msg = session.createTextMessage(message);
			msg.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);//设置是否为持久性消息PERSISTENT持久化消息NON_PERSISTENT非持久化消息
			msg.setJMSPriority(messLevel);//消息优先级,默认为4
			msg.setJMSExpiration(dfaultTime);//消息的有效期0代表永久
			return msg;
		});
	}

	/**
	 * 延迟重复发送消息-非持久化
	 * @param jmsTemplate
	 * @param destination
	 * @param message
	 * @param delay 延迟时间
	 * @param period 每次间隔时间
	 * @param repeat 重复次数
	 */
	protected void sendDelayMessage(JmsTemplate jmsTemplate,final String destination,final String message,
			final long delay,final long period,final int repeat){
		jmsTemplate.send(destination, (session) -> {
			TextMessage msg = session.createTextMessage(message);
			msg.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);//设置是否为持久性消息PERSISTENT持久化消息NON_PERSISTENT非持久化消息
			msg.setJMSPriority(4);//消息优先级,默认为4
			msg.setJMSExpiration(0);//消息的有效期0代表永久
			if(delay>0){
				msg.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
			}
			if(period>0){
				msg.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
			}
			if(repeat>0){
				msg.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
			}
			return msg;
		});
	}

	/**
	 * 表达式发送消息 0 * * * * -非持久化
	 * @param jmsTemplate
	 * @param destination
	 * @param message
	 * @param cron
	 */
	protected void sendCronMessage(JmsTemplate jmsTemplate,final String destination,final String message,final String cron){
		jmsTemplate.send(destination, (session) -> {
			TextMessage msg = session.createTextMessage(message);
			msg.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);//设置是否为持久性消息PERSISTENT持久化消息NON_PERSISTENT非持久化消息
			msg.setJMSPriority(4);//消息优先级,默认为4
			msg.setJMSExpiration(0);//消息的有效期0代表永久
			msg.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON, cron);
			return msg;
		});
	}

	public static JSONObject parseObject(String str){
		try {
			return JSONObject.parseObject(str);
		}catch (Exception e){
			return null;
		}
	}

	protected String addQueueInfo(String queueName, String message,String queueId) {
		String msgstr = message;
		JSONObject json = parseObject(msgstr);
		if(json!=null){
			json.put("destination", queueName);
			json.put("queueId", queueId);
			msgstr = json.toString();
		}
		return msgstr;
	}
}
