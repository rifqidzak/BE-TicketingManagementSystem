package com.lawencon.ticketing.application.dao;

import java.util.List;

import com.lawencon.ticketing.application.model.ProductCustomers;

public interface ProductCustomersDao {
	List<ProductCustomers> getAll();

	ProductCustomers insert(final ProductCustomers data);

	boolean deleteById(final Long id);

	List<ProductCustomers> getByCustomers(final long id);
}
