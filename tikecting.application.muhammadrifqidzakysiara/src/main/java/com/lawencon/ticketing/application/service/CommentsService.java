package com.lawencon.ticketing.application.service;

import com.lawencon.ticketing.application.dto.comments.CommentDto;
import com.lawencon.ticketing.application.dto.comments.CommentInsertDataReqDto;
import com.lawencon.ticketing.application.dto.comments.CommentInsertResDto;

public interface CommentsService {

	public CommentInsertResDto insert(CommentInsertDataReqDto data);

	public CommentDto getAllComment(final Long id);
}
