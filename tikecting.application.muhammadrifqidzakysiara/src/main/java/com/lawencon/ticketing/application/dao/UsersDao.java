package com.lawencon.ticketing.application.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.ticketing.application.model.Users;

public interface UsersDao {
	Users insert(final Users data);

	Users update(final Users data);

	Optional<Users> getById(final Long id);

	List<Users> getAll();

	boolean deleteById(final Long id);

	Optional<Users> getUserByEmail(final String email);


	List<Users> getUsersByRole(final String roleCode);

	List<Users> getPicCustomers(final Long id);
}
