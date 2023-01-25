package com.lawencon.ticketing.application.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.ticketing.application.model.Product;

public interface ProductDao {
	Product insert(final Product data);

	Product update(final Product data);

	Optional<Product> getById(final Long id);

	List<Product> getAll();

	boolean deleteById(final Long id);
}
