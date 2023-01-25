package com.lawencon.ticketing.application.dto.attachmenttickets;

import java.util.List;

public class AttachmentsTicketsDto {
	private List<AttachmentsTicketsDataDto> data;

	public List<AttachmentsTicketsDataDto> getData() {
		return data;
	}

	public void setData(List<AttachmentsTicketsDataDto> data) {
		this.data = data;
	}
}
