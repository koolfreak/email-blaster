/**
 * 
 */
package com.cybernetics.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;


/**
 * @author Emmanuel Nollase
 * @created Feb 12, 2011 - 6:58:02 PM
 */
public class EmailSenderImpl implements EmailSender {

	private static final Log log = LogFactory.getLog(EmailSenderImpl.class);

	private JavaMailSender mailSender;

	private SimpleMailMessage templateMessage;
	
	private VelocityEngine velocityEngine;

	public void setTemplateMessage(SimpleMailMessage templateMessage)
	{
		this.templateMessage = templateMessage;
	}

	public void setMailSender(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	@Override
	public void sendMPEmails(final Map<String, String> infos) {
		
		MimeMessagePreparator emailPrep = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMsg) throws Exception {
				 MimeMessageHelper message = new MimeMessageHelper(mimeMsg);
				 message.setTo(infos.get("to"));
				 message.setFrom(new InternetAddress("eman.nollase@gmail.com",infos.get("tagline")));
				 message.setSubject(infos.get("subject"));
				 
				 String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, infos.get("mailTemplate"), infos);
				 message.setText(text, true);
			}
		};
		this.mailSender.send(emailPrep);
	}
}
