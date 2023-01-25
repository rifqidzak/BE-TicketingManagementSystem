package com.lawencon.ticketing.application.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.ticketing.application.model.Status;


public interface StatusDao {
	Status insert(final Status data);

	Status update(final Status data);

	Optional<Status> getById(final Long id) ;
	
	Optional<Status> getByCode(final String code) ;
	
	List<Status> getAll() ;

	boolean deleteById(final Long id) ;
}
