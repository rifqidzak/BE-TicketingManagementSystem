package com.lawencon.ticketing.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.application.dao.PrioritiesDao;
import com.lawencon.ticketing.application.dto.priorities.PrioritiesDataDto;
import com.lawencon.ticketing.application.dto.priorities.PrioritiesDto;
import com.lawencon.ticketing.application.dto.priorities.PriorityDto;
import com.lawencon.ticketing.application.model.Priorities;
import com.lawencon.ticketing.application.service.PrioritiesService;

@Service
public class PrioritiesServiceImpl implements PrioritiesService {

	private final PrioritiesDao prioritiesDao;

	public PrioritiesServiceImpl(PrioritiesDao prioritiesDao) {
		this.prioritiesDao = prioritiesDao;
	}

	@Override
	public PriorityDto getById(final Long id) {
		final Optional<Priorities> prioritiesOptional = prioritiesDao.getById(id);
		final PrioritiesDataDto prioritiesDataDto = new PrioritiesDataDto();
		prioritiesDataDto.setId(prioritiesOptional.get().getId());
		prioritiesDataDto.setPrioritiesCode(prioritiesOptional.get().getPrioritiesCode());
		prioritiesDataDto.setPrioritiesName(prioritiesOptional.get().getPrioritiesName());
		prioritiesDataDto.setVer(prioritiesOptional.get().getVer());
		final PriorityDto priorityDto = new PriorityDto();
		priorityDto.setData(prioritiesDataDto);

		return priorityDto;
	}

	@Override
	public PrioritiesDto getAll() {
		final List<Priorities> priorities = prioritiesDao.getAll();
		final List<PrioritiesDataDto> prioritiesDataDtos = new ArrayList<>();
		
		for(int i = 0; i<priorities.size();i++) {
			final PrioritiesDataDto prioritiesDataDto = new PrioritiesDataDto();
			prioritiesDataDto.setId(priorities.get(i).getId());
			prioritiesDataDto.setPrioritiesCode(priorities.get(i).getPrioritiesCode());
			prioritiesDataDto.setPrioritiesName(priorities.get(i).getPrioritiesName());
			prioritiesDataDto.setVer(priorities.get(i).getVer());
			prioritiesDataDtos.add(prioritiesDataDto);
		}
		
		final PrioritiesDto prioritiesDto =  new PrioritiesDto();
		prioritiesDto.setData(prioritiesDataDtos);
		return prioritiesDto;
	}

}
