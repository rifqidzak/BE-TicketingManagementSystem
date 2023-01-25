package com.lawencon.ticketing.application.service;

import com.lawencon.ticketing.application.dto.priorities.PrioritiesDto;
import com.lawencon.ticketing.application.dto.priorities.PriorityDto;

public interface PrioritiesService {

	PriorityDto getById(final Long id);

	PrioritiesDto getAll();
}
