package com.lawencon.ticketing.application.dao.impl.nativequerry;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.CompaniesDao;
import com.lawencon.ticketing.application.model.Companies;
@Profile("native")
@Repository
public class CompaniesDaoImpl extends BaseDaoImpl implements CompaniesDao {



	@Override
	public  Companies insert(Companies data)  {
		this.em.persist(data);

		return data;
	}

	@Override
	public  Companies update(final Companies data)  {
		final Companies companiesUpdated = this.em.merge(data);
		return companiesUpdated;
	}

	@Override
	public  Optional<Companies> getById(final Long id)  {
		final Companies companies = this.em.find(Companies.class, id);
		this.em.detach(companies);
		final Optional<Companies> companiesOptional = Optional.ofNullable(companies);
		return companiesOptional;
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<Companies> getAll()  {
		final String sql = "SELECT * FROM companies";
		final List<Companies> companies = this.em.createNativeQuery(sql, Companies.class).getResultList();
		return companies;
	}

	@Override
	public  boolean deleteById(final Long id)  {
		final String sql = "DELETE FROM companies WHERE id = :id";
		final int result = this.em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

}
