package com.lawencon.ticketing.application.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.ticketing.application.model.RoleUsers;


public interface  RoleDao {
	RoleUsers insert(final RoleUsers data) ;

	RoleUsers update(final RoleUsers data) ;

	Optional<RoleUsers> getById(final Long id) ;

	List<RoleUsers> getAll() ;

	boolean deleteById(final Long id) ;
}
