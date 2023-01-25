package com.lawencon.ticketing.application.dao.impl.hql;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.ProductDao;
import com.lawencon.ticketing.application.dao.impl.nativequerry.BaseDaoImpl;
import com.lawencon.ticketing.application.model.Product;

@Profile("hql")
@Repository
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {

	@Override
	public Product insert(final Product data) {
		this.em.persist(data);

		return data;

	}

	@Override
	public Product update(final Product data) {
		final Product productUpdated = this.em.merge(data);
		this.em.flush();
		return productUpdated;
	}

	@Override
	public Optional<Product> getById(final Long id) {
		final Product product = this.em.find(Product.class, id);
		this.em.detach(product);
		final Optional<Product> productOptional = Optional.ofNullable(product);
		return productOptional;
	}

	@Override
	public List<Product> getAll() {
		final String sql = "SELECT p FROM Product p";
		final List<Product> product = this.em.createQuery(sql, Product.class).getResultList();
		return product;
	}

	@Override
	public boolean deleteById(final Long id) {
		final String sql = " DELETE FROM Product WHERE id = :id ";

		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

}
