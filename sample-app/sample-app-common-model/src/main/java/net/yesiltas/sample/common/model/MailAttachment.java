package net.yesiltas.sample.common.model;

import java.io.ByteArrayOutputStream;
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

		ByteArrayOutputStream bos = new ByteArrayOutputStream(inputStream.available());
		try {
			int next = inputStream.read();
			while (next != -1) {
				bos.write(next);
			}
			this.inputStreamSource = new ByteArrayResource(bos.toByteArray());
		} finally {
			inputStream.close();
			bos.close();
		}
	}
}
