package com.lawencon.ticketing.application.repository.jpql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.AttachmentsComments;

@Repository
public interface AttachmentCommentRepositoryJpql extends JpaRepository<AttachmentsComments, Long> {
	
	@Query(value = "select ac from AttachmentsComments as ac "
			+ " INNER JOIN ac.commentsId as c "
			+ "	where c.id = :commentId")
	List<AttachmentsComments> getAllByCommentsId(@Param("commentId") Long commentId);
}
