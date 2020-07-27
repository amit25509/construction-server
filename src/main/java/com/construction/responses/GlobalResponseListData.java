package com.construction.responses;

import java.util.List;

@SuppressWarnings("rawtypes")
public class GlobalResponseListData 
{
	private boolean status;
	private int status_code; 
	private String message;
	private List data;
	
	
	public GlobalResponseListData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GlobalResponseListData(boolean status, int status_code, String message) {
		super();
		this.status = status;
		this.status_code = status_code;
		this.message = message;
	}


	public GlobalResponseListData(boolean status, int status_code, String message, List data) {
		super();
		this.status = status;
		this.status_code = status_code;
		this.message = message;
		this.data = data;
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

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}	
}