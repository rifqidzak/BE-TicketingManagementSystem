package com.lawencon.ticketing.application.dao.impl.nativequerry;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.RoleDao;
import com.lawencon.ticketing.application.model.RoleUsers;
@Profile("native")
@Repository
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

	@Override
	public RoleUsers insert(final RoleUsers data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public RoleUsers update(final RoleUsers data) {
		final RoleUsers roleUsersUpdated = this.em.merge(data);
		return roleUsersUpdated;
	}

	@Override
	public Optional<RoleUsers> getById(final Long id) {
		final RoleUsers roleUsers = this.em.find(RoleUsers.class, id);
		this.em.detach(roleUsers);
		final Optional<RoleUsers> roleUsersOptional = Optional.ofNullable(roleUsers);
		return roleUsersOptional;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleUsers> getAll() {
		final String sql = "SELECT * FROM role_users";
		final List<RoleUsers> roleUsers = this.em.createNativeQuery(sql, RoleUsers.class).getResultList();
		return roleUsers;
	}

	@Override
	public boolean deleteById(final Long id) {
		final String sql = "DELETE FROM role_users WHERE id = :id";
		final int result = this.em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

}
