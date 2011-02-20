/**
 * 
 */
package com.cybernetics;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cybernetics.mail.queue.EmailQueue;

/**
 * @author Emmanuel Nollase
 * @created Feb 12, 2011 - 6:48:57 PM
 */
public class EmailBlasterMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("email-blaster.xml");
		EmailQueue mpQueue = (EmailQueue) ctx.getBean("emailQueue");
		
		//doMPHealth(mpQueue);
		doMPCosmetics(mpQueue);
	}
	
	public static void doMPHealth(EmailQueue mpQueue)
	{
		try {
			Map<String, String> infos = null;
			List<String> emails = FileUtils.readLines(new File("D:\\dev-project\\mp-emails.txt"));
			System.out.println("there are "+emails.size()+" email to be sent");
			for(String info : emails)
			{
				infos = new HashMap<String, String>();
				String infoss[] = StringUtils.split(info, '|');
				infos.put("customer",infoss[0]);
				infos.put("to", infoss[1]);
				infos.put("subject", "Love Your Health");
				infos.put("mailTemplate", "com/cybernetics/templates/mp/mp.vm");
				
				mpQueue.sendMPEmailQueue(infos);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void doMPCosmetics(EmailQueue mpQueue)
	{
		try {
			Map<String, String> infos = null;
			List<String> emails = FileUtils.readLines(new File("D:\\dev-project\\motives-email.txt"));
			System.out.println("there are "+emails.size()+" email to be sent");
			for(String info : emails)
			{
				infos = new HashMap<String, String>();
				String infoss[] = StringUtils.split(info, '|');
				infos.put("customer",infoss[0]);
				infos.put("to", infoss[1]);
				infos.put("subject", "Love Your Health");
				infos.put("mailTemplate", "com/cybernetics/templates/mp/mp.vm");
				
				mpQueue.sendMPEmailQueue(infos);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
