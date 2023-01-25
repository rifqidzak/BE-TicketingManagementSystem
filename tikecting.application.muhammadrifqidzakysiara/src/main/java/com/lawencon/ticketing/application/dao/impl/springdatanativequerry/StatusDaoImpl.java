package com.lawencon.ticketing.application.dao.impl.springdatanativequerry;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.StatusDao;
import com.lawencon.ticketing.application.model.Status;
import com.lawencon.ticketing.application.repository.nativequerry.StatusRepository;

@Profile("SpringDataNativeQuerry")
@Repository
public class StatusDaoImpl implements StatusDao {

	private final StatusRepository statusRepository;

	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	StatusDaoImpl(StatusRepository statusRepository) {
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
		final Object userObj  = statusRepository.getByCode(code);
		Optional<Status> statusOptional = Optional.ofNullable(null);
		if(userObj != null) {
			final Status status = new Status();
			Object[] objArr = (Object[]) userObj;
			status.setId(Long.valueOf(objArr[0].toString()));
			status.setStatusCode(objArr[1].toString());				
			status.setStatusName(objArr[2].toString());
			
			status.setCreatedAt(Timestamp.valueOf(objArr[3].toString()).toLocalDateTime());
			status.setVer(Integer.valueOf(4));
			status.setCreatedBy(Long.valueOf(objArr[5].toString()));
			statusOptional = Optional.ofNullable(status);				
		}
		
		return statusOptional;
	}

	@Override
	public List<Status> getAll() {
		return statusRepository.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		final int result = statusRepository.removeById(id);
		return result > 0;
	}

}
