package com.lawencon.ticketing.application.dto.comments;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.lawencon.ticketing.application.dto.files.FileInsertDataReqDto;

public class CommentInsertDataReqDto {
	private String fillInTheComment;
	
	@NotNull(message = "Ticket Not Found")
	private Long ticketsId;
	
	private List<FileInsertDataReqDto> fileInsertDataReqDtos;

	public String getFillInTheComment() {
		return fillInTheComment;
	}

	public void setFillInTheComment(String fillInTheComment) {
		this.fillInTheComment = fillInTheComment;
	}

	public Long getTicketsId() {
		return ticketsId;
	}

	public void setTicketsId(Long ticketsId) {
		this.ticketsId = ticketsId;
	}

	public List<FileInsertDataReqDto> getFileInsertDataReqDtos() {
		return fileInsertDataReqDtos;
	}

	public void setFileInsertDataReqDtos(List<FileInsertDataReqDto> fileInsertDataReqDtos) {
		this.fileInsertDataReqDtos = fileInsertDataReqDtos;
	}

}
