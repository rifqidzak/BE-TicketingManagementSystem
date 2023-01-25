package com.lawencon.ticketing.application.dao.impl.hql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.CommentsDao;
import com.lawencon.ticketing.application.dao.impl.nativequerry.BaseDaoImpl;
import com.lawencon.ticketing.application.model.Comments;

@Profile("hql")
@Repository
public class CommentsDaoImpl extends BaseDaoImpl implements CommentsDao {

	@Override
	public List<Comments> getAll(final Long ticketsId) {
		final String sql = "SELECT c FROM Comments c " + "INNER JOIN FETCH c.ticketsId " + "INNER JOIN FETCH c.userId "
				+ "WHERE c.ticketsId.id  = :ticketsId " + "ORDER BY c.createdAt DESC";
		final List<Comments> comments = this.em.createQuery(sql, Comments.class).setParameter("ticketsId", ticketsId)
				.getResultList();
		return comments;
	}

	@Override
	public Comments insert(final Comments data) {
		this.em.persist(data);

		return data;
	}

}
