package com.optifyTest;

import java.util.Properties;

/*
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
*/

public class EMail {
	/*
	//EMail class attribues:
	private String to[];	     //Recipient's email ID needs to be mentioned.
	private String from;     //Sender's email ID needs to be mentioned
	private String subject;
	private String text;                     //Message body.
	private String host;       // Assuming you are sending email from localhost
	private Properties properties;           //Get system properties properties.setProperty("mail.smtp.host", host);	 // Setup mail server
	private Session session;                 //Get the default Session object.
	
	
	//EMail class constructor:
	public EMail(String to[],String from,String subject,String text){
		this.to=to;
		this.from=from;
		this.subject=subject;
		this.host="smtp.gmail.com";
		this.text=text;
		
		final String usernme="optifyautomation@gmail.com";
		final String password="OptifyAdmin1";
		
		//Set email server properties
		this.properties=new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
					   "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
		
		this.session = Session.getDefaultInstance(this.properties,new Authenticator(){

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						usernme,password);
			}
		});
	}
	
	//EMail class methods:=====================================================
	public void send(){
		Message message = new MimeMessage(this.session);
		final int SIZE=this.to.length;
		InternetAddress fromAddress = null;
        InternetAddress toAddress[]=new InternetAddress[SIZE];
		
		try{fromAddress = new InternetAddress(this.from);
			for(int i=0;i<SIZE;i++){
				toAddress[i] = new InternetAddress(this.to[i]);
			}
        }catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
        try{message.setFrom(fromAddress);
        	message.addRecipients(RecipientType.TO, toAddress);
        	message.setSubject(this.subject);
        	message.setContent(this.text,"text/html; charset=utf-8");
        	
            Transport.send(message);
            
        }catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	*/
	//=========================================================================
}
