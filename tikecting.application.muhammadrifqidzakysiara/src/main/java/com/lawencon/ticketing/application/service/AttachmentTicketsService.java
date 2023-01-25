package com.lawencon.ticketing.application.service;

import com.lawencon.ticketing.application.dto.attachmenttickets.AttachmentsTicketsDto;

public interface AttachmentTicketsService {
	AttachmentsTicketsDto getByTicketsId(Long id);
}
