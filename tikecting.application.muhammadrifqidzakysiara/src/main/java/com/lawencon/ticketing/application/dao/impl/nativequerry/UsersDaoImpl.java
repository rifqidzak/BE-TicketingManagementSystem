package com.lawencon.ticketing.application.dao.impl.nativequerry;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.UsersDao;
import com.lawencon.ticketing.application.model.Files;
import com.lawencon.ticketing.application.model.RoleUsers;
import com.lawencon.ticketing.application.model.Users;

@Profile("native")
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

		return userUpdated;
	}

	@Override
	public Optional<Users> getById(final Long id) {
		final Users user = this.em.find(Users.class, id);
		this.em.detach(user);
		final Optional<Users> userOptional = Optional.ofNullable(user);
		return userOptional;
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<Users> getAll() {
		final String sql = "SELECT * FROM users INNER JOIN role_users ON users.role_users_id = role_users.id";
		final List<Users> users = this.em.createNativeQuery(sql, Users.class).getResultList();
		return users;

	}

	@Override
	public  boolean deleteById(final Long id) {
		final String sql = " DELETE FROM users WHERE id = :id ";

		final int result = this.em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	@SuppressWarnings("null")
	@Override
	public  Optional<Users> getUserByEmail(final String email) {
		Optional<Users> userOptional = Optional.ofNullable(null);
		final String sql = "SELECT users.id, users.full_name, role_users.role_users_code, users.password,users.files_id, users.ver FROM users INNER JOIN role_users ON role_users.id = users.role_users_id "
				+ "LEFT JOIN companies ON companies.id = users.companies_id WHERE email iLike :email AND users.is_active = true";
		try {
			final Object userObj = this.em.createNativeQuery(sql).setParameter("email", email).getSingleResult();
			if (userObj != null) {
				final Users user = new Users();
				final Object[] objArr = (Object[]) userObj;
				user.setId(Long.valueOf(objArr[0].toString()));
				user.setFullName(objArr[1].toString());
				final Files file = new Files();
				file.setId(Long.valueOf(objArr[4].toString()));
				user.setFilesId(file);
				user.setVer(Integer.valueOf(objArr[5].toString()));
				final RoleUsers roleUsers = new RoleUsers();
				roleUsers.setRoleUserCode(objArr[2].toString());
				user.setRoleUserId(roleUsers);
				user.setPassword(objArr[3].toString());
				userOptional = Optional.ofNullable(user);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userOptional;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getUsersByRole(final String roleCode) {
		final String sql = "SELECT * " + " FROM users INNER JOIN role_users ON role_users.id = users.role_users_id "
				+ " LEFT JOIN companies ON companies.id = users.companies_id "
				+ "	LEFT JOIN users as pic ON users.id = pic.pic_id "
				+ " WHERE role_users.role_users_code iLike :roleCode ";
		final List<Users> users = this.em.createNativeQuery(sql, Users.class).setParameter("roleCode", roleCode)
				.getResultList();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getPicCustomers(final Long id) {
		final String sql = "SELECT * "
				+ " FROM users INNER JOIN companies ON companies.id = users.companies_id where pic_id = :id ";
		final List<Users> users = this.em.createNativeQuery(sql, Users.class).setParameter("id", id).getResultList();
		return users;
	}
}
