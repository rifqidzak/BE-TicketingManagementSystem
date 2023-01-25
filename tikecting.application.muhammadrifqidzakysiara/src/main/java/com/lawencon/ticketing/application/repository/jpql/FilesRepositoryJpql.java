package com.lawencon.ticketing.application.repository.jpql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.Files;

@Repository
public interface FilesRepositoryJpql extends JpaRepository<Files, Long>{

}
