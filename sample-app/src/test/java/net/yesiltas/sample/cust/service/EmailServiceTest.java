package net.yesiltas.sample.cust.service;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.yesiltas.sample.MyApplicationInitializer;
import net.yesiltas.sample.common.model.MailAttachment;
import net.yesiltas.sample.common.service.EmailService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={MyApplicationInitializer.class})

public class EmailServiceTest {
	

	
	@Autowired
	EmailService emailService;
	
	@Test
	public void testSendEmail(){
		MailAttachment attachment;
		try {
			attachment = new MailAttachment("OpenMe.pdf", ClassLoader.getSystemResourceAsStream("ruhsat.pdf"), "application/pdf");
			emailService.sendEmail(null, new String[]{"meltem@yesiltas.net"}, null, null, "Test Message From Meltem", "Test Message From Meltem Content", new MailAttachment[]{attachment});
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertFalse(true);
		}

		
	}

}
