package net.yesiltas.sample.common.model;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;

public class MailAttachment {
	private String fileName;
	private String contentType;
	private InputStreamSource inputStreamSource;

	public MailAttachment(String fileName, InputStream inputStream, String contentType) throws IOException {
		this.setInputStreamSource(inputStream);
		this.setContentType(contentType);
		this.setFileName(fileName);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public InputStreamSource getInputStreamSource() {
		return inputStreamSource;
	}

	public void setInputStreamSource(InputStream inputStream) throws IOException {
		byte[] bytes = new byte[inputStream.available()];
		inputStream.read(bytes);
		this.inputStreamSource = new ByteArrayResource(bytes);
	}
}
