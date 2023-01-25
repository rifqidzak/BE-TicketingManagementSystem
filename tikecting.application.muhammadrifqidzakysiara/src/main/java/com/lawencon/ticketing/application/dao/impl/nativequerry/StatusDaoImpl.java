package com.lawencon.ticketing.application.dao.impl.nativequerry;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.StatusDao;
import com.lawencon.ticketing.application.model.Status;
@Profile("native")
@Repository
public class StatusDaoImpl  extends BaseDaoImpl implements StatusDao {

	@Override
	public Status insert(Status data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public Status update(Status data) {
		final Status statusUpdated = this.em.merge(data);
		return statusUpdated;
	}

	@Override
	public Optional<Status> getById(Long id) {
		final Status status = this.em.find(Status.class, id);
		this.em.detach(status);
		final Optional<Status> statusOptional = Optional.ofNullable(status);
		return statusOptional;
	}

	@Override
	public Optional<Status> getByCode(final String code) {
		final String sql = "SELECT id, status_code, status_name, created_at, ver, created_by FROM status WHERE status_code = :statusCode ";
		Optional<Status> statusOptional = Optional.ofNullable(null);
		try {
			final 	Object userObj = this.em.createNativeQuery(sql)
					.setParameter("statusCode", code)
					.getSingleResult();
			if(userObj != null) {
				Status status = new Status();
				Object[] objArr = (Object[]) userObj;
				status.setId(Long.valueOf(objArr[0].toString()));
				status.setStatusCode(objArr[1].toString());				
				status.setStatusName(objArr[2].toString());
				
				status.setCreatedAt(Timestamp.valueOf(objArr[3].toString()).toLocalDateTime());
				status.setVer(Integer.valueOf(4));
				status.setCreatedBy(Long.valueOf(objArr[5].toString()));
				statusOptional = Optional.ofNullable(status);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusOptional;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> getAll() {
		final String sql = "SELECT * FROM status";
		final List<Status> status = this.em.createNativeQuery(sql, Status.class).getResultList();
		return status;
	}

	@Override
	public boolean deleteById(final Long id) {
		final String sql = " DELETE FROM status WHERE id = :id ";

		final int result = this.em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	

}
