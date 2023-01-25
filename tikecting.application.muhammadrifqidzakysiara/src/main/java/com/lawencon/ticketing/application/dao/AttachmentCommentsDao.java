package com.lawencon.ticketing.application.dao;

import java.util.List;

import com.lawencon.ticketing.application.model.AttachmentsComments;

public interface AttachmentCommentsDao {
	AttachmentsComments insert(final AttachmentsComments data);
	List<AttachmentsComments> getAllByCommentsId(Long commentId);
}
