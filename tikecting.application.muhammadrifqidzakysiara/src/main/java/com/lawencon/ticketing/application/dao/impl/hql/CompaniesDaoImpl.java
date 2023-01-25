package com.lawencon.ticketing.application.dao.impl.hql;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.CompaniesDao;
import com.lawencon.ticketing.application.dao.impl.nativequerry.BaseDaoImpl;
import com.lawencon.ticketing.application.model.Companies;

@Profile("hql")
@Repository
public class CompaniesDaoImpl extends BaseDaoImpl implements CompaniesDao {

	@Override
	public Companies insert(Companies data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public Companies update(final Companies data) {
		final Companies companiesUpdated = this.em.merge(data);
		this.em.flush();
		return companiesUpdated;
	}

	@Override
	public Optional<Companies> getById(final Long id) {
		final Companies companies = this.em.find(Companies.class, id);
		this.em.detach(companies);
		final Optional<Companies> companiesOptional = Optional.ofNullable(companies);
		return companiesOptional;
	}

	@Override
	public List<Companies> getAll() {
		final String sql = "SELECT c FROM Companies c";
		final List<Companies> companies = this.em.createQuery(sql, Companies.class).getResultList();
		return companies;
	}

	@Override
	public boolean deleteById(final Long id) {
		final String sql = "DELETE FROM Companies c WHERE c.id = :id";
		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

}
