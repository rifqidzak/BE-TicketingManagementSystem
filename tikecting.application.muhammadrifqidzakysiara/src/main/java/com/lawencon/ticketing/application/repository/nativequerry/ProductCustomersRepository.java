package com.lawencon.ticketing.application.repository.nativequerry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.ProductCustomers;
@Repository
public interface ProductCustomersRepository extends JpaRepository<ProductCustomers, Long> {
	int removeById(Long id);
	
	@Query(value = " SELECT *  FROM product_customers "
			+ "INNER JOIN users ON product_customers.customers_id = users.id "
			+ "INNER JOIN product ON product_customers.product_id = product.id WHERE customers_id = :customerId ", nativeQuery = true)
	List<ProductCustomers> getByCustomers(@Param("customerId") Long id);
}
