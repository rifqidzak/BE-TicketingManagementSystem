package com.lawencon.ticketing.application.dao.impl.hql;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.RoleDao;
import com.lawencon.ticketing.application.dao.impl.nativequerry.BaseDaoImpl;
import com.lawencon.ticketing.application.model.RoleUsers;

@Profile("hql")
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

	@Override
	public List<RoleUsers> getAll() {
		final String sql = "SELECT ru FROM RoleUsers ru";
		final List<RoleUsers> roleUsers = this.em.createQuery(sql, RoleUsers.class).getResultList();
		return roleUsers;
	}

	@Override
	public boolean deleteById(final Long id) {
		final String sql = "DELETE FROM RoleUsers WHERE id = :id";
		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

}
