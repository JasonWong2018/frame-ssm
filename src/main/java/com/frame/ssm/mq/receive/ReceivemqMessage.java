package com.frame.ssm.mq.receive;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("receivemqMessage")
public class ReceivemqMessage implements MessageListener{

	private static final Logger logger = Logger.getLogger(ReceivemqMessage.class);
	
	@Override
	public void onMessage(Message mess) {
		TextMessage msg = (TextMessage) mess;
		try {
			logger.info("QueueReceiver1接收到消息:"+msg.getText());
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
	}
}
