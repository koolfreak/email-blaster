package com.cybernetics.mail.queue;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jms.core.JmsTemplate;

public class EmailQueueImpl implements EmailQueue {

	private JmsTemplate jmsTemplate;
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendMPEmailQueue(Map<String, String> mpInfos) {
		Map<String, Map<String, String>> infos = new HashMap<String, Map<String,String>>();
		infos.put("mpInfos", mpInfos);
		jmsTemplate.convertAndSend(infos);
	}
	
}
