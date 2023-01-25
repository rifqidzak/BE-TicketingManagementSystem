package com.lawencon.ticketing.application.repository.nativequerry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.RoleUsers;
@Repository
public interface RoleUsersRepository extends JpaRepository<RoleUsers, Long>{
	int removeById(Long id);
}
