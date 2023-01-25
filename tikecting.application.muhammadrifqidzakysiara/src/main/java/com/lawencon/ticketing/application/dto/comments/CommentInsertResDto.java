package com.lawencon.ticketing.application.dto.comments;

public class CommentInsertResDto {
	private CommentInsertDataResDto data;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CommentInsertDataResDto getData() {
		return data;
	}

	public void setData(CommentInsertDataResDto data) {
		this.data = data;
	}

}
