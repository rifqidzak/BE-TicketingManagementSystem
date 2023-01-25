package com.lawencon.ticketing.application.repository.jpql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.Status;

@Repository
public interface StatusRepositoryJpql extends JpaRepository<Status, Long> {
	int removeById(Long id);
	
	@Query(value = "SELECT s FROM Status s WHERE s.statusCode = :statusCode")
	Optional<Status> getByCode(@Param("statusCode") String code);
}
