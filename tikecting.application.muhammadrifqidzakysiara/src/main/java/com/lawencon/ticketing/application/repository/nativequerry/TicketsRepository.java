package com.lawencon.ticketing.application.repository.nativequerry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.Tickets;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {
	@Query(value = "SELECT * FROM tickets " + " INNER JOIN product ON product.id = tickets.product_id "
			+ " INNER JOIN status ON status.id = tickets.status_id "
			+ " INNER JOIN priorities ON priorities.id = tickets.priorities_id "
			+ " INNER JOIN users ON users.id = tickets.customers_id WHERE users.id = :userId", nativeQuery = true)
	List<Tickets> getAllCustomersSides(@Param("userId") Long id);

	@Query(value = "SELECT * FROM tickets " + " INNER JOIN product ON product.id = tickets.product_id "
			+ " INNER JOIN status ON status.id = tickets.status_id "
			+ " INNER JOIN priorities ON priorities.id = tickets.priorities_id "
			+ " INNER JOIN users ON users.id = tickets.customers_id WHERE pic_id = :userId", nativeQuery = true)
	List<Tickets> getAllPicSides(@Param("userId") Long id);
	
	@Query(value = "SELECT * from tickets t "
			+ "INNER JOIN priorities p ON p.id = t.priorities_id "
			+ "WHERE extract (month from t.created_at) = extract(month from now()) "
			+ "AND p.priorities_code ilike :code AND t.customers_id = :customersId", nativeQuery = true)
	List<Tickets>countPriorities(@Param("code")String code, @Param("customersId")Long customersId);
	
}
