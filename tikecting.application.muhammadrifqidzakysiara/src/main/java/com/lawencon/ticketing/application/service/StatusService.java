package com.lawencon.ticketing.application.service;

import com.lawencon.ticketing.application.dto.status.StatusDto;
import com.lawencon.ticketing.application.dto.status.StatusesDto;

public interface StatusService {

	StatusDto getById(final Long id);

	StatusesDto getAll();

}
