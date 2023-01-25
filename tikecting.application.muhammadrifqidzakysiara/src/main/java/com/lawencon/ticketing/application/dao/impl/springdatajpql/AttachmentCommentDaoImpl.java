package com.lawencon.ticketing.application.dao.impl.springdatajpql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.AttachmentCommentsDao;
import com.lawencon.ticketing.application.model.AttachmentsComments;
import com.lawencon.ticketing.application.repository.jpql.AttachmentCommentRepositoryJpql;
@Profile("SpringDataJpql")
@Repository
public class AttachmentCommentDaoImpl implements AttachmentCommentsDao{
	private final AttachmentCommentRepositoryJpql attachmentCommentRepository;
	
	
	public AttachmentCommentDaoImpl(final AttachmentCommentRepositoryJpql attachmentCommentRepository) {
		this.attachmentCommentRepository = attachmentCommentRepository;
	}


	@Override
	public AttachmentsComments insert(final AttachmentsComments data) {
		return attachmentCommentRepository.save(data);
	}


	@Override
	public List<AttachmentsComments> getAllByCommentsId(Long commentId) {
		return attachmentCommentRepository.getAllByCommentsId(commentId);
	}




}
