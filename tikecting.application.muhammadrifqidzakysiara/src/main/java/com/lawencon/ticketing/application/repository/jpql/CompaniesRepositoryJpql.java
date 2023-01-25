package com.lawencon.ticketing.application.repository.jpql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.Companies;

@Repository
public interface CompaniesRepositoryJpql extends JpaRepository<Companies, Long>{
	int removeById(Long id);
}
