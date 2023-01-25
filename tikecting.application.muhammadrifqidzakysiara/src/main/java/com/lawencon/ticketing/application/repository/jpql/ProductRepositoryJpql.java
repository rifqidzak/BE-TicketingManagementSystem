package com.lawencon.ticketing.application.repository.jpql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.Product;

@Repository
public interface ProductRepositoryJpql extends JpaRepository<Product, Long>{
	int removeById(Long id);
}
