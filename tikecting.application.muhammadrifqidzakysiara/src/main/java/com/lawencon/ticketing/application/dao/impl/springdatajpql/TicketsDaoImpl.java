package com.lawencon.ticketing.application.dao.impl.springdatajpql;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.TicketsDao;
import com.lawencon.ticketing.application.model.Tickets;
import com.lawencon.ticketing.application.repository.jpql.TicketsRepositoryJpql;

@Profile("SpringDataJpql")
@Repository
public class TicketsDaoImpl implements TicketsDao {
	private final TicketsRepositoryJpql ticketsRepository;
	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	TicketsDaoImpl(TicketsRepositoryJpql ticketsRepository) {
		this.ticketsRepository = ticketsRepository;
	}

	@Override
	public Tickets insert(final Tickets data) {
		return ticketsRepository.save(data);
	}

	@Override
	public List<Tickets> getAllCustomersSides(final Long id) {
		return ticketsRepository.getAllCustomersSides(id);
	}

	@Override
	public Tickets update(final Tickets data) {
		return ticketsRepository.saveAndFlush(data);
	}

	@Override
	public Optional<Tickets> getById(final Long id) {
		final Tickets tickets = ticketsRepository.findById(id).get();
		em.detach(tickets);
		
		return Optional.ofNullable(tickets);
	}

	@Override
	public List<Tickets> getAllPicSides(final Long id) {
		return ticketsRepository.getAllPicSides(id);
	}

	@Override
	public List<Tickets> countPriority(final String code, final Long customersId) {
		return ticketsRepository.countPriorities(code,customersId);
	}

	@Override
	public List<Tickets> getAllCustomersSidesByCompany(Long id) {
		return ticketsRepository.getAllCustomersSidesByCompany(id);
	}

}
