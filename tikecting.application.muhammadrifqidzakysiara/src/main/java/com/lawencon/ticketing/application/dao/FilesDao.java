package com.lawencon.ticketing.application.dao;

import java.util.Optional;

import com.lawencon.ticketing.application.model.Files;

public interface FilesDao {
	Files insert(final Files data);

	Optional<Files> getById(final Long id);

}
