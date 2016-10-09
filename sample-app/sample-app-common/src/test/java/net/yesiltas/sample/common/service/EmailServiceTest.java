package net.yesiltas.sample.common.service;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.yesiltas.sample.common.CommonApplicationInitializer;
import net.yesiltas.sample.common.model.MailAttachment;

/**
 * For testing {@link EmailService}
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
		attachment = new MailAttachment("OpenMe.pdf", ClassLoader.getSystemResourceAsStream("ruhsat.pdf"), "application/pdf");
		emailService.sendEmail(null, new String[] { "meltem@yesiltas.net" }, null, null, "Test Message From Meltem",
				"Test Message From Meltem Content", new MailAttachment[] { attachment });
		emailService.sendEmail("meltem.yesiltas@gmail.com", new String[] { "meltem@yesiltas.net" }, new String[] { "meltem@yesiltas.net" }, new String[] { "meltem@yesiltas.net" }, "Test Message From Meltem",
				"Test Message From Meltem Content", new MailAttachment[] { attachment });
	}

}
