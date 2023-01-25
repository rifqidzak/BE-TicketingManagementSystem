package com.lawencon.ticketing.application.dao;

import java.util.List;

import com.lawencon.ticketing.application.model.Comments;

public interface CommentsDao {
	List<Comments> getAll(final Long ticketsId);

	Comments insert(final Comments data);
}
