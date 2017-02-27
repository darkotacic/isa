package com.isa.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	
	public SendEmail(String sendTo, String link){
		  // Recipient's email ID needs to be mentioned.
	      String to = sendTo;

	      // Sender's email ID needs to be mentioned
	      String from = "isa.user.mail@gmail.com";
	      final String username = "isa.user.mail@gmail.com";//change accordingly
	      final String password = "dedamraz";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");
	      Session.getInstance(props, null);
	      
	      Session session = Session.getDefaultInstance(props);
	      MimeMessage message = new MimeMessage(session);
	      
	      try
	      {
	          message.setFrom(new InternetAddress(from));
	          message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	          message.setSubject("Aktivacioni link");
	          message.setText("Aktivirajte nalog klikom na link: "+link, "utf-8", "html");
	          Transport.send(message, username	, password);
	      }
	      catch (AddressException e) {e.printStackTrace();}
	      catch (MessagingException e) {e.printStackTrace();}
	}
	
}