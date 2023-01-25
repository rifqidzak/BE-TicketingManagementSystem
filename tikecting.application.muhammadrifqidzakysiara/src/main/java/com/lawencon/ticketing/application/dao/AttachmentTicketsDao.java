package com.lawencon.ticketing.application.dao;

import java.util.List;

import com.lawencon.ticketing.application.model.AttachmentsTickets;

public interface AttachmentTicketsDao {
	AttachmentsTickets insert(final AttachmentsTickets data);
	List<AttachmentsTickets> getAllByTicketsId(Long ticketId);
}
