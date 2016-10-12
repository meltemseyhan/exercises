package net.yesiltas.sample.cust.service;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import net.yesiltas.sample.common.CommonApplicationInitializer;
import net.yesiltas.sample.common.model.MailAttachment;
import net.yesiltas.sample.common.service.EmailService;
import static org.junit.Assert.*;

/**
 * For testing {@link EmailService} Normally this service is implemented and
 * tested in the common project, but to be sure that we can use services from
 * common project the test is also executed here
 * 
 * @author Meltem
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CommonApplicationInitializer.class })
public class EmailServiceTest {

	@Autowired
	EmailService emailService;

	/**
	 * Tests email send service with different parameters
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSendEmail() throws IOException {
		MailAttachment attachment;
		attachment = new MailAttachment("OpenMe.pdf", ClassLoader.getSystemResourceAsStream("ruhsat.pdf"),
				"application/pdf");
		ResponseEntity<?> response = emailService.sendEmail(null, new String[] { "meltem@yesiltas.net" }, null, null,
				"Test Message From Meltem", "Test Message From Meltem Content", new MailAttachment[] { attachment });
		assertEquals(HttpStatus.OK, response.getStatusCode());
		response = emailService.sendEmail("sezer@yesiltas.net", new String[] { "meltem@yesiltas.net", "meltem.yesiltas@gmail.com" },
				new String[] { "meltem@yesiltas.net" }, new String[] { "meltem@yesiltas.net" },
				"Test Message From Meltem", "Test Message From Meltem Content", new MailAttachment[] { attachment });
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
