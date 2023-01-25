package com.lawencon.ticketing.application.dao.impl.hql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.AttachmentCommentsDao;
import com.lawencon.ticketing.application.dao.impl.nativequerry.BaseDaoImpl;
import com.lawencon.ticketing.application.model.AttachmentsComments;

@Profile("hql")
@Repository
public class AttachmentCommentsDaoImpl extends BaseDaoImpl implements AttachmentCommentsDao {

	@Override
	public AttachmentsComments insert(final AttachmentsComments data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public List<AttachmentsComments> getAllByCommentsId(Long commentId) {
		final String sql = "select ac from AttachmentsComments as ac "
				+ " INNER JOIN ac.commentsId as c "
				+ "	where c.id = :commentId";
		List<AttachmentsComments> attachmentsComments = this.em.createQuery(sql, AttachmentsComments.class)
				.setParameter("commentId", commentId)
				.getResultList();
		return attachmentsComments;
	}

}
