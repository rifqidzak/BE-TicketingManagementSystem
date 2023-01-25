package com.lawencon.ticketing.application.dao.impl.springdatajpql;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.CompaniesDao;
import com.lawencon.ticketing.application.model.Companies;
import com.lawencon.ticketing.application.repository.jpql.CompaniesRepositoryJpql;
@Profile("SpringDataJpql")
@Repository
public class CompaniesDaoImpl implements CompaniesDao{
	
	private final CompaniesRepositoryJpql companiesRepository;
	
	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public CompaniesDaoImpl(final CompaniesRepositoryJpql companiesRepository) {
		this.companiesRepository = companiesRepository;
	}

	@Override
	public Companies insert(final Companies data) {
		return companiesRepository.save(data);
	}

	@Override
	public Companies update(final Companies data) {
		return companiesRepository.saveAndFlush(data);
	}

	@Override
	public Optional<Companies> getById(final Long id) {
		final Companies companies = companiesRepository.findById(id).get();
		em.detach(companies);
		
		return Optional.ofNullable(companies);
	}

	@Override
	public List<Companies> getAll() {
		return companiesRepository.findAll();
	}

	@Override
	public boolean deleteById(final Long id) {
		final int result = companiesRepository.removeById(id);
		return result > 0;
	}

}
