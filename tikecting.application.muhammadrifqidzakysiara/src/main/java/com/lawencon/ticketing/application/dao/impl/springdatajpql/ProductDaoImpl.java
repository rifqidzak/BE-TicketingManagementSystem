package com.lawencon.ticketing.application.dao.impl.springdatajpql;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.ProductDao;
import com.lawencon.ticketing.application.model.Product;
import com.lawencon.ticketing.application.repository.jpql.ProductRepositoryJpql;
@Profile("SpringDataJpql")
@Repository
public class ProductDaoImpl implements ProductDao {
	private final ProductRepositoryJpql productRepository;
	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	public ProductDaoImpl(ProductRepositoryJpql productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product insert(final Product data) {
		return productRepository.save(data);
	}

	@Override
	public Product update(final Product data) {
		return productRepository.saveAndFlush(data);
	}

	@Override
	public Optional<Product> getById(final Long id) {
		final Product product = productRepository.findById(id).get();
		em.detach(product);
		
		return Optional.ofNullable(product);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public boolean deleteById(final Long id) {
		final int result = productRepository.removeById(id);

		return result > 0;
	}

}
