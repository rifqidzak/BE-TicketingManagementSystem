package com.lawencon.ticketing.application.repository.jpql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.RoleUsers;
@Repository
public interface RoleUsersRepositoryJpql extends JpaRepository<RoleUsers, Long>{
	int removeById(Long id);
}
