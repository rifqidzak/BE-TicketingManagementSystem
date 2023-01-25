package com.lawencon.ticketing.application.dao.impl.nativequerry;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.ProductDao;
import com.lawencon.ticketing.application.model.Product;
@Profile("native")
@Repository
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {

	@Override
	public  Product insert(final Product data)  {
		this.em.persist(data);

		return data;
	}

	@Override
	public  Product update(final Product data)  {
		final Product productUpdated = this.em.merge(data);
		return productUpdated;
	}

	@Override
	public  Optional<Product> getById(final Long id)  {
		final Product product = this.em.find(Product.class, id);
		this.em.detach(product);
		final Optional<Product> productOptional = Optional.ofNullable(product);
		return productOptional;
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<Product> getAll()  {
		final String sql = "SELECT * FROM product";
		final List<Product> product = this.em.createNativeQuery(sql, Product.class).getResultList();
		return product;
	}

	@Override
	public  boolean deleteById(Long id)  {
		final String sql = " DELETE FROM product WHERE id = :id ";

		final int result = this.em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

}
