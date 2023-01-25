package com.lawencon.ticketing.application.dao.impl.nativequerry;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.AttachmentCommentsDao;
import com.lawencon.ticketing.application.model.AttachmentsComments;
@Profile("native")
@Repository
public class AttachmentCommentsDaoImpl extends BaseDaoImpl implements AttachmentCommentsDao {

	@Override
	public AttachmentsComments insert(final AttachmentsComments data) {
		this.em.persist(data);

		return data;
	}
	
	@Override
	public List<AttachmentsComments> getAllByCommentsId(Long commentId) {
		final String sql = "select * from attachments_comments ac2 where comments_id = :commentId";
		List<AttachmentsComments> attachmentsComments = this.em.createQuery(sql, AttachmentsComments.class)
				.setParameter("commentId", commentId)
				.getResultList();
		return attachmentsComments;
	}

}
