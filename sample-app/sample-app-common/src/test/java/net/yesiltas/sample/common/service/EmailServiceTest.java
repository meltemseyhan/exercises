package net.yesiltas.sample.common.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import net.yesiltas.sample.common.CommonApplicationInitializer;
import net.yesiltas.sample.common.model.MailAttachment;
import net.yesiltas.sample.common.model.SampleResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CommonApplicationInitializer.class })

public class EmailServiceTest {

	//@MockBean
	@Autowired
	EmailService emailService;

	//@Before
	public void setup() {
		when(this.emailService.sendEmail(anyString(), any(String[].class), any(String[].class), any(String[].class),
				anyString(), anyString(), any(MailAttachment[].class)))
						.thenReturn(new SampleResponse(true, null, null));
	}

	@Test
	public void testSendEmail() {
		MailAttachment attachment;
		try {
			attachment = new MailAttachment("OpenMe.pdf", ClassLoader.getSystemResourceAsStream("ruhsat.pdf"),
					"application/pdf");
			emailService.sendEmail(null, new String[] { "meltem@yesiltas.net" }, null, null, "Test Message From Meltem",
					"Test Message From Meltem Content", new MailAttachment[] { attachment });
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertFalse(true);
		}

	}

}
