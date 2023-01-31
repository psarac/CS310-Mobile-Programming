package com.sabanciuniv.model;

public class Message<T> {
	
	private String message;
	private T data;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(String message, T data) {
		super();
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	

}
