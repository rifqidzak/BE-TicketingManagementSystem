package com.lawencon.ticketing.application.dto.tickets;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lawencon.ticketing.application.dto.files.FileInsertDataReqDto;

public class TicketsInsertDataReqDto {
	@NotBlank(message = "Title Required")
	@Size(max = 30, message = "Title Range 0-30")
	private String titleTickets;

	@NotBlank(message = "Body Ticket Required")
	private String fillInTickets;

	@NotNull(message = "Product Required")
	private Long productId;

	@NotNull(message = "Priorities Required")
	private Long prioritiesId;
	
	
	private List<FileInsertDataReqDto> fileInsertDataReqDtos;

	public String getTitleTickets() {
		return titleTickets;
	}

	public void setTitleTickets(String titleTickets) {
		this.titleTickets = titleTickets;
	}

	public String getFillInTickets() {
		return fillInTickets;
	}

	public void setFillInTickets(String fillInTickets) {
		this.fillInTickets = fillInTickets;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getPrioritiesId() {
		return prioritiesId;
	}

	public void setPrioritiesId(Long prioritiesId) {
		this.prioritiesId = prioritiesId;
	}

	public List<FileInsertDataReqDto> getFileInsertDataReqDtos() {
		return fileInsertDataReqDtos;
	}

	public void setFileInsertDataReqDtos(List<FileInsertDataReqDto> fileInsertDataResDtos) {
		this.fileInsertDataReqDtos = fileInsertDataResDtos;
	}
}
