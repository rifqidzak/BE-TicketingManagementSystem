package com.lawencon.ticketing.application.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.ticketing.application.model.Tickets;

public interface TicketsDao {
	Tickets insert(final Tickets data) ;
	List <Tickets> getAllCustomersSides(final Long id) ;
	Tickets update (final Tickets data) ;
	Optional<Tickets> getById(final Long id) ;
	List <Tickets> getAllPicSides(final Long id) ;
	List<Tickets> countPriority(final String code, final Long customersId);
	List <Tickets> getAllCustomersSidesByCompany(final Long id) ;
}
