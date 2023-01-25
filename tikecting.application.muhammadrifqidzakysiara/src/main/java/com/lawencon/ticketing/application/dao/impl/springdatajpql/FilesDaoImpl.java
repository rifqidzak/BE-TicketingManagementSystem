package com.lawencon.ticketing.application.dao.impl.springdatajpql;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.FilesDao;
import com.lawencon.ticketing.application.model.Files;
import com.lawencon.ticketing.application.repository.jpql.FilesRepositoryJpql;
@Profile("SpringDataJpql")
@Repository
public class FilesDaoImpl implements FilesDao{
	private final FilesRepositoryJpql filesRepository;
	
	private EntityManager em;

	@PersistenceContext
	public void setEm(final EntityManager em) {
		this.em = em;
	}
	
	public FilesDaoImpl(final FilesRepositoryJpql filesRepository) {
		this.filesRepository = filesRepository;
	}

	@Override
	public Files insert(final Files data) {
		return filesRepository.save(data);
	}

	@Override
	public Optional<Files> getById(final Long id) {
		final Files files = filesRepository.findById(id).get();
		em.detach(files);
		
		return Optional.ofNullable(files);
	}
}
