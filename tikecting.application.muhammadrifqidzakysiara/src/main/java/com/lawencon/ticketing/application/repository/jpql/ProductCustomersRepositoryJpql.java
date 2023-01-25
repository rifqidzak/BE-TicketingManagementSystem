package com.lawencon.ticketing.application.repository.jpql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.ProductCustomers;
@Repository
public interface ProductCustomersRepositoryJpql extends JpaRepository<ProductCustomers, Long> {
	int removeById(Long id);
	
	@Query(value = " SELECT pc FROM ProductCustomers pc" + " INNER JOIN FETCH pc.customersId "
			+ " INNER JOIN FETCH pc.productId WHERE pc.customersId.id = :customerId ")
	List<ProductCustomers> getByCustomers(@Param("customerId") Long id);
}
