package com.lawencon.ticketing.application.dao.impl.hql;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.FilesDao;
import com.lawencon.ticketing.application.dao.impl.nativequerry.BaseDaoImpl;
import com.lawencon.ticketing.application.model.Files;

@Profile("hql")
@Repository
public class FilesDaoImpl extends BaseDaoImpl implements FilesDao {

	@Override
	public Files insert(final Files data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public Optional<Files> getById(final Long id) {
		final Files files = this.em.find(Files.class, id);
		this.em.detach(files);
		final Optional<Files> filesOptional = Optional.ofNullable(files);
		return filesOptional;
	}


}
