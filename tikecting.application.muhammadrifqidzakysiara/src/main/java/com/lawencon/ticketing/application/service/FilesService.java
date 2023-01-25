package com.lawencon.ticketing.application.service;

import java.util.Optional;

import com.lawencon.ticketing.application.model.Files;

public interface FilesService {

	Optional<Files> getById(final Long id);

}
