package com.lawencon.ticketing.application.repository.nativequerry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.Files;

@Repository
public interface FilesRepository extends JpaRepository<Files, Long>{

}
