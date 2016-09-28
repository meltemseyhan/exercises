package net.yesiltas.sample.common.model;

public class SampleResponse {
	private boolean success;
	private String message;
	private Object result;
	
	public SampleResponse(boolean success, String message, Object result){
		this.setSuccess(success);
		this.setMessage(message);
		this.setResult(result);
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
}
