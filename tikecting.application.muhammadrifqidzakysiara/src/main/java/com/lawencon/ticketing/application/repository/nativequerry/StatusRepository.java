package com.lawencon.ticketing.application.repository.nativequerry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
	int removeById(Long id);
	
	@Query(value = "SELECT id, status_code, status_name, created_at, ver, created_by FROM status WHERE status_code = :statusCode", nativeQuery = true)
	Object getByCode(@Param("statusCode") String code);
}
