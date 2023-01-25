package com.lawencon.ticketing.application.dto.comments;

import java.util.List;

public class CommentDto {
	private List<CommentDataDto> data;

	public List<CommentDataDto> getData() {
		return data;
	}

	public void setData(List<CommentDataDto> data) {
		this.data = data;
	}
}
