package net.yesiltas.sample.common.model;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;

/**
 * Class to contain email attachment info. Mail API expects an InputStreamSource
 * object that will be fresh all the time. This is why ByteArrayResource is
 * used.
 * 
 * @author Meltem
 */
public class MailAttachment {
	private String fileName;
	private String contentType;
	private InputStreamSource inputStreamSource;

	/**
	 * Constructs mail attachment object with the given parameters
	 * 
	 * @param fileName
	 *            file name to use in email
	 * @param inputStream
	 *            input stream for reading attachment content
	 * @param contentType
	 *            mime type of the attachment (e.g. "application/pdf")
	 * @throws IOException
	 *             if input stream cannot be read
	 */
	public MailAttachment(String fileName, InputStream inputStream, String contentType) throws IOException {
		this.setInputStreamSource(inputStream);
		this.setContentType(contentType);
		this.setFileName(fileName);
	}

	/**
	 * @return file name that will be used in email
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            file name that will be used in email
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return mime type of the attachment (e.g. "application/pdf")
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            mime type of the attachment (e.g. "application/pdf")
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return InputStreamSource containing attachment content
	 */
	public InputStreamSource getInputStreamSource() {
		return inputStreamSource;
	}

	/**
	 * Converts the given InputStream to ByteArrayResource to make sure it is
	 * always fresh as mail API expects
	 * 
	 * @param inputStream
	 *            input stream to read attachment content
	 * @throws IOException
	 */
	public void setInputStreamSource(InputStream inputStream) throws IOException {
		byte[] bytes = new byte[inputStream.available()];
		inputStream.read(bytes); // NOSONAR
		this.inputStreamSource = new ByteArrayResource(bytes);
	}
}
