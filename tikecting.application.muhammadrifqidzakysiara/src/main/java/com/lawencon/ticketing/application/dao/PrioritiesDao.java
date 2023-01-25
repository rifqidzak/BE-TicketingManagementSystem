package com.lawencon.ticketing.application.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.ticketing.application.model.Priorities;

public interface PrioritiesDao {
	Priorities insert(final Priorities data) ;

	Priorities update(final Priorities data) ;

	Optional<Priorities> getById(final Long id) ;

	List<Priorities> getAll() ;

	boolean deleteById(final Long id);
	
}
