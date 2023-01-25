package com.lawencon.ticketing.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.application.dao.AttachmentTicketsDao;
import com.lawencon.ticketing.application.dto.attachmenttickets.AttachmentsTicketsDataDto;
import com.lawencon.ticketing.application.dto.attachmenttickets.AttachmentsTicketsDto;
import com.lawencon.ticketing.application.model.AttachmentsTickets;
import com.lawencon.ticketing.application.service.AttachmentTicketsService;

@Service
public class AttachmentTicketsServiceImpl implements AttachmentTicketsService{
	private final AttachmentTicketsDao attachmentTicketsDao;
	
	public AttachmentTicketsServiceImpl(AttachmentTicketsDao attachmentTicketsDao) {
		this.attachmentTicketsDao = attachmentTicketsDao;
	}
	@Override
	public AttachmentsTicketsDto getByTicketsId(Long id) {
		List<AttachmentsTickets> attachmentsTickets = attachmentTicketsDao.getAllByTicketsId(id);
		List<AttachmentsTicketsDataDto> attachmentsTicketsDataDtos = new ArrayList<>();
		for(int i = 0 ; i<attachmentsTickets.size(); i++) {
			AttachmentsTicketsDataDto attachmentsTicketsDataDto = new AttachmentsTicketsDataDto();
			attachmentsTicketsDataDto.setFileId(attachmentsTickets.get(i).getTicketsId().getId());
			attachmentsTicketsDataDto.setTicketId(attachmentsTickets.get(i).getFileId().getId());
			attachmentsTicketsDataDtos.add(attachmentsTicketsDataDto);		
			}
		AttachmentsTicketsDto attachmentsTicketsDto = new AttachmentsTicketsDto();
		attachmentsTicketsDto.setData(attachmentsTicketsDataDtos);
		return attachmentsTicketsDto;
	}

}
