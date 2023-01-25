package com.lawencon.ticketing.application.dao.impl.springdatanativequerry;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.AttachmentTicketsDao;
import com.lawencon.ticketing.application.model.AttachmentsTickets;
import com.lawencon.ticketing.application.repository.nativequerry.AttachmentTicketRepository;
@Profile("SpringDataNativeQuerry")
@Repository
public class AttachmentTicketDaoImpl implements AttachmentTicketsDao{
	private final AttachmentTicketRepository attachmentTicketRepository;
	
	
	public AttachmentTicketDaoImpl(final AttachmentTicketRepository attachmentTicketRepository) {
		this.attachmentTicketRepository = attachmentTicketRepository;
	}

	@Override
	public AttachmentsTickets insert(final AttachmentsTickets data) {
		return attachmentTicketRepository.save(data);
	}

	@Override
	public List<AttachmentsTickets> getAllByTicketsId(Long ticketId) {
		
		return attachmentTicketRepository.getAllByTicketsId(ticketId);
	}

}
