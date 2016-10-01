package net.yesiltas.sample.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import net.yesiltas.sample.common.model.MailAttachment;

@Service
public class EmailService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.default.from.address}")
	private String defaultFrom;
	
	public ResponseEntity<Object> sendEmail(String from, String[] to, String[] cc, String[] bcc, String subject, String content, MailAttachment[] attachments){ //NOSONAR
	        
	    MimeMessagePreparator messagePreparator = mimeMessage -> {
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, attachments.length > 0);
	        messageHelper.setFrom((from == null) ? defaultFrom : from);
	        messageHelper.setTo((to == null) ? new String[0] : to);
	        messageHelper.setCc((cc == null) ? new String[0] : cc);
	        messageHelper.setBcc((bcc == null) ? new String[0] : bcc);
	        messageHelper.setSubject(subject);
	        messageHelper.setText(content, true);
	        for (MailAttachment attachment: attachments) {
	        	messageHelper.addAttachment(attachment.getFileName(), attachment.getInputStreamSource(), attachment.getContentType());
			}
	    }; 
	    try {    	
	    	logger.debug("Sending email....");
	        mailSender.send(messagePreparator);
	        logger.debug("Email is sent...");
	        return ResponseEntity.ok().body(null);
	    } catch (MailException e) {
	        logger.error(e.getMessage(), e);
	        //todo: sending the exception in response body may not be a good design //NOSONAR
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
	    }		
	}
	
}
