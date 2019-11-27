package com.viajesgala.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {
  
    @Autowired
    public JavaMailSender emailSender;
    
    @Value("${spring.mail.username}")
    private String username;
    
    @Value("${spring.mail.password}")
    private String password;
 
    public void sendSimpleMessage(
      String to, String subject, String text) {
        
    	try {
	    	System.out.println("mail ==> sendSimpleMessage ==> to: "+to);
	    	System.out.println("mail ==> sendSimpleMessage ==> subject: "+subject);
	    	System.out.println("mail ==> sendSimpleMessage ==> text: "+text);
	    	System.out.println("mail ==> sendSimpleMessage ==> username: "+username);
	    	System.out.println("mail ==> sendSimpleMessage ==> password: "+password);
	    	SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setTo(to); 
	        message.setSubject(subject); 
	        message.setText(text);
	        emailSender.send(message);
    	} catch (Exception e) {
    		System.out.println("mail ==> sendSimpleMessage ==> excepcion: "+e.getMessage());
    		throw e;
    	}
    }
}