package com.lawencon.ticketing.application.dao.impl.springdatajpql;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.PrioritiesDao;
import com.lawencon.ticketing.application.model.Priorities;
import com.lawencon.ticketing.application.repository.jpql.PrioritiesRepositoryJpql;
@Profile("SpringDataJpql")
@Repository
public class PrioritiesDaoImpl implements PrioritiesDao {
	private final PrioritiesRepositoryJpql prioritiesRepository;
	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	public PrioritiesDaoImpl(PrioritiesRepositoryJpql prioritiesRepository) {
		this.prioritiesRepository = prioritiesRepository;
	}

	@Override
	public Priorities insert(Priorities data) {
		return prioritiesRepository.save(data);
	}

	@Override
	public Priorities update(Priorities data) {
		return prioritiesRepository.saveAndFlush(data);
	}

	@Override
	public Optional<Priorities> getById(Long id) {
		final Priorities priorities = prioritiesRepository.findById(id).get();
		em.detach(priorities);
		
		return Optional.ofNullable(priorities);
	}

	@Override
	public List<Priorities> getAll() {
		return prioritiesRepository.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		final int result = prioritiesRepository.removeById(id);
		return result > 0;
	}

}
