package com.lawencon.ticketing.application.dao.impl.springdatanativequerry;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.AttachmentCommentsDao;
import com.lawencon.ticketing.application.model.AttachmentsComments;
import com.lawencon.ticketing.application.repository.nativequerry.AttachmentCommentRepository;
@Profile("SpringDataNativeQuerry")
@Repository
public class AttachmentCommentDaoImpl implements AttachmentCommentsDao{
	private final AttachmentCommentRepository attachmentCommentRepository;
	
	
	public AttachmentCommentDaoImpl(final AttachmentCommentRepository attachmentCommentRepository) {
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
