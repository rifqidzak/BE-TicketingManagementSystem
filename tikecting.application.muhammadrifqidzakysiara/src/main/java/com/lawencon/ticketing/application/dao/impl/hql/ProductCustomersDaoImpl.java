package com.lawencon.ticketing.application.dao.impl.hql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.ProductCustomersDao;
import com.lawencon.ticketing.application.dao.impl.nativequerry.BaseDaoImpl;
import com.lawencon.ticketing.application.model.ProductCustomers;

@Profile("hql")
@Repository
public class ProductCustomersDaoImpl extends BaseDaoImpl implements ProductCustomersDao {

	@Override
	public List<ProductCustomers> getAll() {
		final String sql = " SELECT pc FROM ProductCustomers pc" + " INNER JOIN FETCH pc.customersId "
				+ " INNER JOIN FETCH pc.productId ";
		final List<ProductCustomers> productCustomers = this.em.createQuery(sql, ProductCustomers.class)
				.getResultList();
		return productCustomers;
	}

	@Override
	public ProductCustomers insert(final ProductCustomers data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public boolean deleteById(final Long id) {
		final String sql = "DELETE FROM ProductCustomers WHERE id = :id";

		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	@Override
	public List<ProductCustomers> getByCustomers(final long id) {
		final String sql = " SELECT pc FROM ProductCustomers pc" + " INNER JOIN FETCH pc.customersId "
				+ " INNER JOIN FETCH pc.productId WHERE pc.customersId.id = :customerId ";
		final List<ProductCustomers> productCustomers = this.em.createQuery(sql, ProductCustomers.class)
				.setParameter("customerId", id).getResultList();
		return productCustomers;
	}

}
