package com.lawencon.ticketing.application.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.application.dao.FilesDao;
import com.lawencon.ticketing.application.model.Files;
import com.lawencon.ticketing.application.service.FilesService;

@Service
public class FilesServiceImpl implements FilesService {
	private final FilesDao filesDao;
	public FilesServiceImpl(FilesDao filesDao) {
		this.filesDao = filesDao;
	}

	@Override
	public Optional<Files> getById(final Long id) {
		return filesDao.getById(id);
	}
}
