package com.lawencon.ticketing.application.repository.jpql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.Users;

@Repository
public interface UsersRepositoryJpql extends JpaRepository<Users, Long> {

	int removeById(Long id);

	@Query(value = "SELECT u FROM Users u "
			+ " LEFT JOIN u.companiesId " + " WHERE u.email = :email AND u.isActive = true")
	Users getByEmail(@Param("email") String email);

	@Query(value = "SELECT u" + " FROM Users u " + " LEFT JOIN FETCH u.roleUserId ru"
			+ "	LEFT JOIN FETCH u.picId pic " + " LEFT JOIN FETCH u.companiesId c"
			+ " WHERE ru.roleUserCode = :roleCode ")
	List<Users> getUsersByRole(@Param("roleCode") String roleCode);

	@Query(value = "SELECT u " + " FROM Users u " + " INNER JOIN FETCH u.companiesId c"
			+ "	INNER JOIN FETCH u.picId pic" + " where pic.id = :id ")
	List<Users> getPicCustomers(@Param("id") Long id);

}
