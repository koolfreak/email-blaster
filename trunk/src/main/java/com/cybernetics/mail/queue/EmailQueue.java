/**
 * 
 */
package com.cybernetics.mail.queue;

import java.util.Map;

/**
 * @author Emmanuel Nollase
 * @created Jan 29, 2011 - 8:01:37 PM
 */
public interface EmailQueue {

	void sendMPEmailQueue(Map<String, String> mpInfos);
}
