package com.lawencon.ticketing.application.repository.jpql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.Priorities;
@Repository
public interface PrioritiesRepositoryJpql extends JpaRepository<Priorities, Long> {

	int removeById(Long id);
}
