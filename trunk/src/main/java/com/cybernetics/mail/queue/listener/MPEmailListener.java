/**
 * 
 */
package com.cybernetics.mail.queue.listener;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cybernetics.mail.EmailSender;

/**
 * @author Emmanuel Nollase
 * @created Jan 29, 2011 - 6:48:11 PM
 */
public class MPEmailListener implements MessageListener {

	private static final Log log = LogFactory.getLog(MPEmailListener.class);
	private EmailSender emailSender; 
	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@SuppressWarnings("unchecked")
	public void onMessage(Message message) {
		ActiveMQMapMessage amq = (ActiveMQMapMessage) message;
		try {
			Map<String, String> emailInfos = (Map<String, String>) amq.getObject("mpInfos");
			emailSender.sendEticket(emailInfos);
		} catch (JMSException e) {
			log.error("Error sending email", e);
		}
	}
	
	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}
}
