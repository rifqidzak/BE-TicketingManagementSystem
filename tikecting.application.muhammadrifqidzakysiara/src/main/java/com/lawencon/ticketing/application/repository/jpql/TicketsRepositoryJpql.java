package com.lawencon.ticketing.application.repository.jpql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.Tickets;

@Repository
public interface TicketsRepositoryJpql extends JpaRepository<Tickets, Long> {
	@Query(value = "SELECT t FROM Tickets t" + " INNER JOIN FETCH t.productId "
			+ " INNER JOIN FETCH t.statusId " + " INNER JOIN FETCH t.prioritiesId "
			+ " INNER JOIN FETCH t.customersId " + " WHERE t.customersId.id = :userId")
	List<Tickets> getAllCustomersSides(@Param("userId") Long id);

	@Query(value = "SELECT t FROM Tickets t" + " INNER JOIN t.productId " + " INNER JOIN t.statusId "
			+ " INNER JOIN t.prioritiesId " + " INNER JOIN t.customersId WHERE t.customersId.picId.id = :userId")
	List<Tickets> getAllPicSides(@Param("userId") Long id);
	
	@Query(value = "SELECT t FROM Tickets t" + " INNER JOIN FETCH t.productId "
			+ " INNER JOIN FETCH t.statusId " + " INNER JOIN FETCH t.prioritiesId "
			+ " INNER JOIN FETCH t.customersId as c "
			+ " WHERE c.companiesId.id = :companiesId")
	List<Tickets> getAllCustomersSidesByCompany(@Param("companiesId") Long id);
	
	
	
	@Query(value = "SELECT t from Tickets t "
			+ "	INNER JOIN FETCH t.customersId c"
			+ " INNER JOIN FETCH t.prioritiesId p "
			+ " WHERE month(t.createdAt) = month(current_timestamp()) "
			+ " AND p.prioritiesCode = :code AND c.id = :customersId")
	List<Tickets>countPriorities(@Param("code")String code, @Param("customersId")Long customersId);
}
