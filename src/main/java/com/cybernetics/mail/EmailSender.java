/**
 * 
 */
package com.cybernetics.mail;

import java.util.Map;

/**
 * @author Emmanuel Nollase
 * @created Feb 12, 2011 - 6:58:15 PM
 */
public interface EmailSender {

	void sendMPEmails(final Map<String, String> infos);
}
