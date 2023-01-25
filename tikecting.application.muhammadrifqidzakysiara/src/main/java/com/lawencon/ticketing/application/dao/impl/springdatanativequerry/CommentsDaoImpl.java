package com.lawencon.ticketing.application.dao.impl.springdatanativequerry;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.CommentsDao;
import com.lawencon.ticketing.application.model.Comments;
import com.lawencon.ticketing.application.repository.nativequerry.CommentsRepository;
@Profile("SpringDataNativeQuerry")
@Repository
public class CommentsDaoImpl implements CommentsDao {
	private final CommentsRepository commentsRepository;
	public CommentsDaoImpl(CommentsRepository commentsRepository) {
		this.commentsRepository = commentsRepository;
	}

	@Override
	public List<Comments> getAll(final Long ticketsId) {
		return commentsRepository.findAll();
	}

	@Override
	public Comments insert(final Comments data) {
		return commentsRepository.save(data);
	}

}
