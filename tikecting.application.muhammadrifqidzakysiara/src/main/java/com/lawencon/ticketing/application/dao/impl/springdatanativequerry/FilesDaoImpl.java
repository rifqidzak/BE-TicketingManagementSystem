package com.lawencon.ticketing.application.dao.impl.springdatanativequerry;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.FilesDao;
import com.lawencon.ticketing.application.model.Files;
import com.lawencon.ticketing.application.repository.nativequerry.FilesRepository;
@Profile("SpringDataNativeQuerry")
@Repository
public class FilesDaoImpl implements FilesDao{
	private FilesRepository filesRepository;
	
	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public FilesDaoImpl(FilesRepository filesRepository) {
		this.filesRepository = filesRepository;
	}

	@Override
	public Files insert(final Files data) {
		return filesRepository.save(data);
	}

	@Override
	public Optional<Files> getById(final Long id) {
		Files files = filesRepository.findById(id).get();
		em.detach(files);
		
		return Optional.ofNullable(files);
	}
}
