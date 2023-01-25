package com.lawencon.ticketing.application.dao.impl.springdatajpql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.ProductCustomersDao;
import com.lawencon.ticketing.application.model.ProductCustomers;
import com.lawencon.ticketing.application.repository.jpql.ProductCustomersRepositoryJpql;
@Profile("SpringDataJpql")
@Repository
public class ProductCustomersDaoImpl implements ProductCustomersDao{
	private final ProductCustomersRepositoryJpql productCustomersRepository;
	
	public ProductCustomersDaoImpl(ProductCustomersRepositoryJpql productCustomersRepository) {
		this.productCustomersRepository = productCustomersRepository;
	}

	@Override
	public List<ProductCustomers> getAll() {
		return productCustomersRepository.findAll();
	}

	@Override
	public ProductCustomers insert(final ProductCustomers data) {
		return productCustomersRepository.save(data);
	}

	@Override
	public boolean deleteById(final Long id) {
		final int result = productCustomersRepository.removeById(id);
		return result>0;
	}

	@Override
	public List<ProductCustomers> getByCustomers(final long id) {
		
		return productCustomersRepository.getByCustomers(id);
	}

}
