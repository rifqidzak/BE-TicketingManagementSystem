package com.lawencon.ticketing.application.dao.impl.hql;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.PrioritiesDao;
import com.lawencon.ticketing.application.dao.impl.nativequerry.BaseDaoImpl;
import com.lawencon.ticketing.application.model.Priorities;

@Profile("hql")
@Repository
public class PrioritiesDaoImpl extends BaseDaoImpl implements PrioritiesDao {

	@Override
	public Priorities insert(final Priorities data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public Priorities update(final Priorities data) {
		final Priorities prioritiesUpdated = this.em.merge(data);
		return prioritiesUpdated;
	}

	@Override
	public Optional<Priorities> getById(final Long id) {
		final Priorities priorities = this.em.find(Priorities.class, id);
		this.em.detach(priorities);
		final Optional<Priorities> prioritiesOptional = Optional.ofNullable(priorities);
		return prioritiesOptional;
	}

	@Override
	public List<Priorities> getAll() {
		final String sql = "SELECT p FROM Priorities p";
		final List<Priorities> priorities = this.em.createQuery(sql, Priorities.class).getResultList();
		return priorities;

	}

	@Override
	public boolean deleteById(final Long id) {
		final String sql = "DELETE FROM Priorities WHERE id = :id";

		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

}
