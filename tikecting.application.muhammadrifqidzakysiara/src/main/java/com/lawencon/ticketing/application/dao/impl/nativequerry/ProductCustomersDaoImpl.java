package com.lawencon.ticketing.application.dao.impl.nativequerry;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.ProductCustomersDao;
import com.lawencon.ticketing.application.model.ProductCustomers;
@Profile("native")
@Repository
public class ProductCustomersDaoImpl extends BaseDaoImpl implements ProductCustomersDao {


	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCustomers> getAll()  {
		final String sql = " SELECT * FROM product_customers "
				+ " INNER JOIN users ON product_customers.customers_id = users.id "
				+ " INNER JOIN product ON product_customers.product_id = product.id ";
		final List<ProductCustomers> productCustomers = this.em.createNativeQuery(sql, ProductCustomers.class)
				.getResultList();
		return productCustomers;
	}

	@Override
	public ProductCustomers insert(final ProductCustomers data)  {
		this.em.persist(data);

		return data;
	}

	@Override
	public boolean deleteById(final Long id)  {
		final String sql = "DELETE FROM product_customers WHERE id = :id";

		final int result = this.em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCustomers> getByCustomers(final long id)  {
		final String sql = " SELECT *  FROM product_customers "
				+ "INNER JOIN users ON product_customers.customers_id = users.id "
				+ "INNER JOIN product ON product_customers.product_id = product.id WHERE customers_id = :customerId ";
		final List<ProductCustomers> productCustomers = this.em.createNativeQuery(sql, ProductCustomers.class)
				.setParameter("customerId", id)
				.getResultList();
		return productCustomers;
	}

}
