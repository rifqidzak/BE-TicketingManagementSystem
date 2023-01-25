package com.lawencon.ticketing.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.application.dao.StatusDao;
import com.lawencon.ticketing.application.dto.status.StatusDataDto;
import com.lawencon.ticketing.application.dto.status.StatusDto;
import com.lawencon.ticketing.application.dto.status.StatusesDto;
import com.lawencon.ticketing.application.model.Status;
import com.lawencon.ticketing.application.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {
	private final StatusDao statusDao;

	public StatusServiceImpl(StatusDao statusDao) {
		this.statusDao = statusDao;
	}

	@Override
	public StatusDto getById(final Long id) {
		final Optional<Status> statusOptional = statusDao.getById(id);
		final StatusDataDto statusDataDto = new StatusDataDto();
		statusDataDto.setStatusCode(statusOptional.get().getStatusCode());
		statusDataDto.setStatusName(statusOptional.get().getStatusName());
		statusDataDto.setId(statusOptional.get().getId());
		statusDataDto.setVer(statusOptional.get().getVer());
		final StatusDto statusDto = new StatusDto();
		statusDto.setData(statusDataDto);
		return statusDto;
	}

	@Override
	public StatusesDto getAll() {
		final List<Status> statuses = statusDao.getAll();
		final List<StatusDataDto> statusDataDtos = new ArrayList<>();

		for (int i = 0; i < statuses.size(); i++) {
			StatusDataDto statusDataDto = new StatusDataDto();
			statusDataDto.setStatusName(statuses.get(i).getStatusName());
			statusDataDto.setStatusCode(statuses.get(i).getStatusCode());
			statusDataDto.setId(statuses.get(i).getId());
			statusDataDto.setVer(statuses.get(i).getVer());
			statusDataDtos.add(statusDataDto);
		}

		final StatusesDto statusesDto = new StatusesDto();
		statusesDto.setData(statusDataDtos);
		return statusesDto;
	}

}
