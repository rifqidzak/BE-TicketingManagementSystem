package com.lawencon.ticketing.application.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.ticketing.application.model.Companies;

public interface CompaniesDao {
	Companies insert(final Companies data);

	Companies update(final Companies data);

	Optional<Companies> getById(final Long id);

	List<Companies> getAll();

	boolean deleteById(final Long id);
}
