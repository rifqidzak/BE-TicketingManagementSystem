package com.lawencon.ticketing.application.repository.nativequerry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	int removeById(Long id);

	@Query(value = "SELECT users.id, users.full_name, role_users.role_users_code, users.password, users.files_id, users.ver FROM users INNER JOIN role_users ON role_users.id = users.role_users_id "
			+ "LEFT JOIN companies ON companies.id = users.companies_id WHERE email iLike :email AND users.is_active = true", nativeQuery = true)
	Object auth(@Param("email") String email);

	@Query(value = "SELECT * " + " FROM users INNER JOIN role_users ON role_users.id = users.role_users_id "
			+ " LEFT JOIN companies ON companies.id = users.companies_id "
			+ "	LEFT JOIN users as pic ON users.id = pic.pic_id "
			+ " WHERE role_users.role_users_code iLike :roleCode ", nativeQuery = true)
	List<Users> getUsersByRole(@Param("roleCode") String roleCode);
	
	@Query(value = "SELECT * "
			+ " FROM users INNER JOIN companies ON companies.id = users.companies_id where pic_id = :id ", nativeQuery = true)
	List<Users> getPicCustomers(@Param("id") Long id);

}
