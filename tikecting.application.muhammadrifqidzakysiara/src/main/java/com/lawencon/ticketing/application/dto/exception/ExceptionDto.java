package com.lawencon.ticketing.application.dto.exception;

public class ExceptionDto<T>{
	private T message;

	public T getMessage() {
		return message;
	}

	public void setMessage(T message) {
		this.message = message;
	}


}
