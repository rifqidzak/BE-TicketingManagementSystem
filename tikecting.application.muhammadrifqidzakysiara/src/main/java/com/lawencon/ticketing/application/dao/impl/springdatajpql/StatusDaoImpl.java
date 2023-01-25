package com.lawencon.ticketing.application.dao.impl.springdatajpql;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.StatusDao;
import com.lawencon.ticketing.application.model.Status;
import com.lawencon.ticketing.application.repository.jpql.StatusRepositoryJpql;

@Profile("SpringDataJpql")
@Repository
public class StatusDaoImpl implements StatusDao {

	private final StatusRepositoryJpql statusRepository;

	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	StatusDaoImpl(StatusRepositoryJpql  statusRepository) {
		this.statusRepository = statusRepository;
	}

	@Override
	public Status insert(final Status data) {
		return statusRepository.save(data);
	}

	@Override
	public Status update(final Status data) {
		return statusRepository.saveAndFlush(data);
	}

	@Override
	public Optional<Status> getById(final Long id) {
		final Status status = statusRepository.findById(id).get();
		em.detach(status);
		
		return Optional.ofNullable(status);
	}

	@Override
	public Optional<Status> getByCode(final String code) {
		final Status status = statusRepository.getByCode(code).get();
		em.detach(status);
		
		return Optional.ofNullable(status);
	}

	@Override
	public List<Status> getAll() {
		return statusRepository.findAll();
	}

	@Override
	public boolean deleteById(final Long id) {
		final int result = statusRepository.removeById(id);
		return result > 0;
	}

}
