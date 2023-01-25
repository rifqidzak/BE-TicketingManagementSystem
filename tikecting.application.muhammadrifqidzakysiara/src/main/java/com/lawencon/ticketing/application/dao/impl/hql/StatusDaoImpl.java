package com.lawencon.ticketing.application.dao.impl.hql;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.StatusDao;
import com.lawencon.ticketing.application.dao.impl.nativequerry.BaseDaoImpl;
import com.lawencon.ticketing.application.model.Status;

@Profile("hql")
@Repository
public class StatusDaoImpl extends BaseDaoImpl implements StatusDao {

	@Override
	public Status insert(final Status data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public Status update(final Status data) {
		final Status statusUpdated = this.em.merge(data);
		return statusUpdated;
	}

	@Override
	public Optional<Status> getById(final Long id) {
		final Status status = this.em.find(Status.class, id);
		this.em.detach(status);
		final Optional<Status> statusOptional = Optional.ofNullable(status);
		return statusOptional;
	}

	@Override
	public List<Status> getAll() {
		final String sql = "SELECT s FROM Status s";
		final List<Status> status = this.em.createQuery(sql, Status.class).getResultList();
		return status;
	}

	@Override
	public boolean deleteById(final Long id) {
		final String sql = " DELETE FROM Status WHERE id = :id ";

		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	@Override
	public Optional<Status> getByCode(final String code) {
		final String sql = "SELECT s.id, s.statusCode, s.statusName, s.ver FROM Status s WHERE s.statusCode = :statusCode ";
		Optional<Status> statusOptional = Optional.ofNullable(null);

		try {
			final Object userObj = this.em.createQuery(sql).setParameter("statusCode", code).getSingleResult();
			if (userObj != null) {
				Status status = new Status();
				Object[] objArr = (Object[]) userObj;
				status.setId(Long.valueOf(objArr[0].toString()));
				status.setStatusCode(objArr[1].toString());
				status.setStatusName(objArr[2].toString());
				status.setVer(Integer.valueOf(objArr[3].toString()));
				statusOptional = Optional.ofNullable(status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusOptional;
	}

}
