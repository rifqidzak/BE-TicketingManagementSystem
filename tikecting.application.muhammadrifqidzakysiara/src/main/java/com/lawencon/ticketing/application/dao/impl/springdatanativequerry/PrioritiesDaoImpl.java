package com.lawencon.ticketing.application.dao.impl.springdatanativequerry;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.PrioritiesDao;
import com.lawencon.ticketing.application.model.Priorities;
import com.lawencon.ticketing.application.repository.nativequerry.PrioritiesRepository;
@Profile("SpringDataNativeQuerry")
@Repository
public class PrioritiesDaoImpl implements PrioritiesDao {
	PrioritiesRepository prioritiesRepository;
	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	public PrioritiesDaoImpl(PrioritiesRepository prioritiesRepository) {
		this.prioritiesRepository = prioritiesRepository;
	}

	@Override
	public Priorities insert(final Priorities data) {
		return prioritiesRepository.save(data);
	}

	@Override
	public Priorities update(final Priorities data) {
		return prioritiesRepository.saveAndFlush(data);
	}

	@Override
	public Optional<Priorities> getById(final Long id) {
		final Priorities priorities = prioritiesRepository.findById(id).get();
		em.detach(priorities);
		
		return Optional.ofNullable(priorities);
	}

	@Override
	public List<Priorities> getAll() {
		return prioritiesRepository.findAll();
	}

	@Override
	public boolean deleteById(final Long id) {
		final int result = prioritiesRepository.removeById(id);
		return result > 0;
	}

}
