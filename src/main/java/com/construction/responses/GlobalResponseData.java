package com.construction.responses;

public class GlobalResponseData 
{
	private boolean status;
	private int status_code;
	private String message;
	private Object data;
	public GlobalResponseData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GlobalResponseData(boolean status, int status_code, String message, Object data) {
		super();
		this.status = status;
		this.status_code = status_code;
		this.message = message;
		this.data= data;
	}
	public GlobalResponseData(boolean status, int status_code, String message) {
		super();
		this.status = status;
		this.status_code = status_code;
		this.message = message;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getStatus_code() {
		return status_code;
	}
	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
