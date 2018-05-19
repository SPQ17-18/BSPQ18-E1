package es.deusto.bspq.cinema.server.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * Class to send the emails
 * 
 * @author anderarguinano
 *
 */

public class MailSender {

	private static final Logger logger = Logger.getLogger(MailSender.class);

	private final String from = "deusto.sd@gmail.com";
	private final String password = "softwaredesign";

	private final String host = "smtp.gmail.com";
	private final String port = "587";

	private String to;

	private Properties props;

	/**
	 * Constructor for the mail sender
	 * 
	 * @param destination
	 *            String with the email of the destination
	 */
	public MailSender(String destination) {
		to = destination;
		props = new Properties();
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "false");
	}

	/**
	 * Method for sending the message
	 * 
	 * @param text
	 *            String with the message we would like to send
	 * @param subject
	 *            Subject for the email
	 * @return Returns true when the message is succesfully send
	 */

	public boolean sendMessage(String text, String subject) {
		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);

			MimeMessage msg = new MimeMessage(session);
			msg.setText(text.trim());
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport.send(msg);
			logger.info("Message sent to: " + to);
		} catch (Exception ex) {
			logger.error(" $ Error sending message: " + ex);
			return false;
		}
		return true;
	}

	/**
	 * Authenticator for the email account
	 * 
	 * @author anderarguinano
	 *
	 */
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(from, password);
		}
	}

}
