package com.lawencon.ticketing.application.dao.impl.hql;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.UsersDao;
import com.lawencon.ticketing.application.dao.impl.nativequerry.BaseDaoImpl;
import com.lawencon.ticketing.application.model.Users;

@Profile("hql")
@Repository
public class UsersDaoImpl extends BaseDaoImpl implements UsersDao {

	@Override
	public Users insert(Users data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public Users update(final Users data) {
		final Users userUpdated = this.em.merge(data);
		this.em.flush();
		return userUpdated;
	}

	@Override
	public Optional<Users> getById(final Long id) {
		final Users user = this.em.find(Users.class, id);
		this.em.detach(user);
		final Optional<Users> userOptional = Optional.ofNullable(user);
		return userOptional;
	}

	@Override
	public List<Users> getAll() {
		final String sql = "SELECT u FROM Users u INNER JOIN FETCH u.roleUserId";
		final List<Users> users = this.em.createQuery(sql, Users.class).getResultList();
		return users;

	}

	@Override
	public boolean deleteById(final Long id) {
		final String sql = " DELETE FROM Users WHERE id = :id ";

		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	@Override
	public Optional<Users> getUserByEmail(final String email) {
		Optional<Users> userOptional = Optional.ofNullable(null);
		final String sql = "SELECT u FROM Users u "
				+ " LEFT JOIN u.companiesId " + " WHERE u.email = :email AND u.isActive = true";
		final Users users = this.em.createQuery(sql, Users.class).setParameter("email", email).getSingleResult();
		userOptional = Optional.ofNullable(users);
		return userOptional;
	}

	@Override
	public List<Users> getUsersByRole(final String roleCode) {
		final String sql = "SELECT u" + " FROM Users u " + " INNER JOIN FETCH u.roleUserId ru"
				+ "	LEFT JOIN FETCH u.picId pic " + " LEFT JOIN FETCH u.companiesId c"
				+ " WHERE ru.roleUserCode = :roleCode ";
		final List<Users> users = this.em.createQuery(sql, Users.class).setParameter("roleCode", roleCode)
				.getResultList();
		return users;
	}

	@Override
	public List<Users> getPicCustomers(final Long id) {
		final String sql = "SELECT u " + " FROM Users u " + " INNER JOIN FETCH u.companiesId c"
				+ "	INNER JOIN FETCH u.picId pic" + " where pic.id = :id ";
		final List<Users> users = this.em.createQuery(sql, Users.class).setParameter("id", id).getResultList();
		return users;
	}
}
