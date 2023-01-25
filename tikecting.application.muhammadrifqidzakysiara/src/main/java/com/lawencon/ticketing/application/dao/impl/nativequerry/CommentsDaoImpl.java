package com.lawencon.ticketing.application.dao.impl.nativequerry;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.CommentsDao;
import com.lawencon.ticketing.application.model.Comments;
@Profile("native")
@Repository
public class CommentsDaoImpl extends BaseDaoImpl implements CommentsDao {


	@SuppressWarnings("unchecked")
	@Override
	public List<Comments> getAll(final Long ticketsId)  {
		final String sql = "SELECT * FROM comments as c "
				+ " INNER JOIN tickets t on c.tickets_id = t.id " + " INNER JOIN users u on c.users_id  = u.id "
				+ " WHERE c.tickets_id  = :ticketsId " + " ORDER BY c.created_at DESC";
		final List<Comments> comments = this.em.createNativeQuery(sql, Comments.class)
				.setParameter("ticketsId", ticketsId)
				.getResultList();
		return comments;
	}

	@Override
	public Comments insert(final Comments data)  {
		this.em.persist(data);

		return data;
	}

}
