package com.lawencon.ticketing.application.service;

import com.lawencon.ticketing.application.dto.tickets.TicketDto;
import com.lawencon.ticketing.application.dto.tickets.TicketInsertResDto;
import com.lawencon.ticketing.application.dto.tickets.TicketUpdateResDto;
import com.lawencon.ticketing.application.dto.tickets.TicketsDto;
import com.lawencon.ticketing.application.dto.tickets.TicketsInsertDataReqDto;

public interface TicketsService {
	TicketInsertResDto insert(TicketsInsertDataReqDto data);

	TicketsDto getAllCustomersSides(final Long id);

	TicketsDto getAllPicSides(final Long id);
	
	TicketDto getById(final Long id);

	TicketUpdateResDto update(final Long id);
}
